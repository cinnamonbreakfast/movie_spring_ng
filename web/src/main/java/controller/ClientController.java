package controller;

import converter.ClientConverter;
import dto.ClientDTO;
import dto.ClientsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ClientService;

import java.util.List;

@RestController
public class ClientController {
    public static final Logger log= LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientConverter clientConverter;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    ClientsDTO getClients() {
        log.trace("getClients - method entered");
        return new ClientsDTO(clientConverter.convertModelsToDtos(clientService.getAll()));
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    void saveClient(@RequestBody ClientDTO clientDTO) {
        log.trace("saveClient - method entered: clientDTO={}", clientDTO);
        clientService.addClient(
            clientConverter.convertDtoToModel(clientDTO)
        );
        log.trace("saveClient - method finished");
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    void updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        log.trace("updateClient - method entered: id={}, clientDTO={}", id, clientDTO);

        clientDTO.setId(id);
        clientService.updateClient(
                clientConverter.convertDtoToModel(clientDTO)
        );

        log.trace("updateClient - method finished");
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
    ClientDTO getByID(@PathVariable Long id) {
        log.trace("getByID - method entered: id={}", id);

        return clientConverter.convertModelToDto(
                clientService.getByID(id)
        );
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Long id){
        log.trace("deleteClient - method entered: id={}", id);

        clientService.removeClient(id);

        log.trace("deleteClient - method finished");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/clients/filter/firstName/{firstName}", method = RequestMethod.GET)
    ClientsDTO filterClientsByFirstName(@PathVariable String firstName)
    {
        log.trace("filterClientsByFirstName - method entered: firstName={}", firstName);

        return new ClientsDTO(
                clientConverter.convertModelsToDtos(
                        clientService.filterClientsByFirstName(firstName)
                )
        );
    }

    @RequestMapping(value = "/clients/filter/secondName/{secondName}", method = RequestMethod.GET)
    ClientsDTO filterClientsBySecondName(@PathVariable String secondName)
    {
        log.trace("filterClientsBySecondName - method entered: secondName={}", secondName);

        return new ClientsDTO(
                clientConverter.convertModelsToDtos(
                        clientService.filterClientsByLastName(secondName)
                )
        );
    }

    @RequestMapping(value = "/clients/filter/age/{age}", method = RequestMethod.GET)
    ClientsDTO filterClientsByAge(@PathVariable int age)
    {
        log.trace("filterClientsByAge - method entered: age={}", age);
        return new ClientsDTO(
                clientConverter.convertModelsToDtos(
                        clientService.filterClientsByAge(age)
                )
        );
    }

    @RequestMapping(value = "/clients/sorted/asc/{fields}", method = RequestMethod.GET)
    ClientsDTO getAllSortedAscendingByFields(@PathVariable String fields)
    {
        log.trace("getAllSortedAscendingByFields - method entered: fields={}", fields);
        return new ClientsDTO(
                clientConverter.convertModelsToDtos(
                        clientService.getAllSortedAscendingByFields(fields)
                )
        );
    }

    @RequestMapping(value = "/clients/sorted/desc/{fields}", method = RequestMethod.GET)
    ClientsDTO getAllSortedDescendingByFields(@PathVariable String fields)
    {
        log.trace("getAllSortedDescendingByFields - method entered: fields={}", fields);
        return new ClientsDTO(
                clientConverter.convertModelsToDtos(
                        clientService.getAllSortedDescendingByFields(fields)
                )
        );
    }
}
