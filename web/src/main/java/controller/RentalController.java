package controller;

import converter.RentalConverter;
import dto.RentalDTO;
import dto.RentalsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.RentalService;

import java.util.List;

@RestController
public class RentalController {
    public static final Logger log= LoggerFactory.getLogger(RentalController.class);

    @Autowired
    private RentalService rentalService;

    @Autowired
    private RentalConverter rentalConverter;

    @RequestMapping(value = "/rentals", method = RequestMethod.GET)
    RentalsDTO getRentals() {
        log.trace("getRentals - method entered");
        return new RentalsDTO(rentalConverter.convertModelsToDtos(rentalService.getAll()));
    }

    @RequestMapping(value = "/rentals", method = RequestMethod.POST)
    void saveRental(@RequestBody RentalDTO rentalDTO) {
        log.trace("saveRental - method entered: rentalDTO={}", rentalDTO);
        rentalService.addRental(
                rentalConverter.convertDtoToModel(rentalDTO)
        );
        log.trace("saveRental - method finished");
    }

    @RequestMapping(value = "/rentals/{id}", method = RequestMethod.GET)
    RentalDTO getByID(@PathVariable Long id) {
        log.trace("getByID - method entered: id={}", id);

        return rentalConverter.convertModelToDto(
                rentalService.getByID(id)
        );
    }

    @RequestMapping(value = "/rentals/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteRental(@PathVariable Long id){
        log.trace("deleteRental - method entered: id={}", id);

        rentalService.removeRental(id);

        log.trace("deleteRental - method finished");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/rentals/sorted/asc/{fields}", method = RequestMethod.GET)
    RentalsDTO getAllSortedAscendingByFields(@PathVariable String fields)
    {
        log.trace("getAllSortedAscendingByFields - method entered: fields={}", fields);
        return new RentalsDTO(
                rentalConverter.convertModelsToDtos(
                        rentalService.getAllSortedAscendingByFields(fields)
                )
        );
    }

    @RequestMapping(value = "/rentals/sorted/desc/{fields}", method = RequestMethod.GET)
    RentalsDTO getAllSortedDescendingByFields(@PathVariable String fields)
    {
        log.trace("getAllSortedDescendingByFields - method entered: fields={}", fields);
        return new RentalsDTO(
                rentalConverter.convertModelsToDtos(
                        rentalService.getAllSortedDescendingByFields(fields)
                )
        );
    }
}
