package com.mpp.movie_rental.controller;

import com.mpp.movie_rental.converter.ClientConverter;
import com.mpp.movie_rental.dto.ClientDTO;
import com.mpp.movie_rental.dto.ClientsDTO;
import com.mpp.movie_rental.dto.PageDTO;
import com.mpp.movie_rental.dto.SortDTO;
import domain.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.jaxb.SpringDataJaxb.PageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.ClientService;

import java.util.*;
import java.util.stream.StreamSupport;

@RestController
public class ClientController {
    public static final Logger log= LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientConverter clientConverter;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    List<ClientDTO> getClients() {
        log.trace("getClients - method entered");

        List<Client> clientDTOS = clientService.getAll();

        return new ArrayList<>(clientConverter.convertModelsToDtos(clientDTOS));
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    ClientDTO saveClient(@RequestBody ClientDTO clientDTO) {
        log.trace("saveClient - method entered: clientDTO={}", clientDTO);
        clientDTO.setId(null);

        return clientConverter.convertModelToDto(clientService.addClient(
                clientConverter.convertDtoToModel(clientDTO)
        ));
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

        this.clientService.removeClient(id);

        log.trace("deleteClient - method finished");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/clients/page/{page}/{count}/filter", method  = RequestMethod.GET)
    ClientsDTO filterBy(@RequestParam(required = false) String firstName, @RequestParam(required = false) String secondName, @RequestParam(required = false) String job, @RequestParam(required = false) Integer age, @PathVariable Integer page, @PathVariable Integer count)
    {
        log.trace("filterBy - method entered: firstName={}, secondName={}, job={}, age={}", firstName, secondName, job, age);

        Client client = new Client();

        client.setFirstName(firstName);
        client.setSecondName(secondName);
        client.setJob(job);
        client.setAge(age);

        Page<Client> pageable = clientService.filterBy(Example.of(client), PageRequest.of(page, count, Sort.by("firstName").ascending()));

        ClientsDTO clientsDTO = new ClientsDTO();
        clientsDTO.setClients(clientConverter.convertModelsToDtos(pageable.getContent()));
        clientsDTO.setPage(page);
        clientsDTO.setPagesCount(pageable.getTotalPages());

        return clientsDTO;
    }

    @RequestMapping(value = "/clients/sorted/asc/{fields}", method = RequestMethod.GET)
    List<ClientDTO> getAllSortedAscendingByFields(@PathVariable String[] fields)
    {
        log.trace("getAllSortedAscendingByFields - method entered: fields={}", (Object) fields);
        return new ArrayList<>(
                clientConverter.convertModelsToDtos(
                        clientService.getAllSortedAscendingByFields(fields)
                )
        );
    }

    @RequestMapping(value = "/clients/sorted/desc/{fields}", method = RequestMethod.GET)
    List<ClientDTO> getAllSortedDescendingByFields(@PathVariable String[] fields)
    {
        log.trace("getAllSortedDescendingByFields - method entered: fields={}", (Object) fields);
        return new ArrayList<>(
                clientConverter.convertModelsToDtos(
                        clientService.getAllSortedDescendingByFields(fields)
                )
        );
    }

    @RequestMapping(value = "/clients/page/total/{page}/{count}", method = RequestMethod.GET)
    Integer getPages(@PathVariable Integer count, @PathVariable Integer page)
    {
        log.trace("getPages - method entered: count={}, page={}", count, page);

        return clientService.findAll(PageRequest.of(page, count)).getTotalPages();
    }

    @RequestMapping(value = "/clients/page/{page}/{count}", method = RequestMethod.GET)
    List<ClientDTO> getPage(@PathVariable Integer count, @PathVariable Integer page)
    {
        log.trace("getPages - method entered: count={}, page={}", count, page);

        return new ArrayList<>(
                clientConverter.convertModelsToDtos(
                        clientService.findAll(
                                PageRequest.of(
                                        page,
                                        count,
                                        Sort.by("firstName")
                                )
                        )
                                .getContent()
                )
        );
    }

    @RequestMapping(value = "/clients/page/{page}/{size}", method = RequestMethod.POST)
    ClientsDTO getPage(@PathVariable Integer page, @PathVariable Integer size, @RequestBody(required = false) ClientDTO probe, @RequestParam(required = false) String[] sorting)
    {
        log.trace("getPage - method entered: page={}, size={}, probe={}, sort={}", page, size, probe, sorting);

        Sort sortingP = Sort.by("firstName").ascending();;

        if(sorting != null) {
            if (sorting.length > 0 && sorting.length % 2 == 0) {
                Arrays.stream(sorting).reduce((e, v) -> {
                    if (v.equals("ASC")) {
                        sortingP.and(Sort.by(e).ascending());
                    } else {
                        sortingP.and(Sort.by(e).descending());
                    }
                    return "";
                });
            }
        }

        Pageable pageable = PageRequest.of(page, size);
        pageable.getSort().and(sortingP);

        log.trace("sorting={}", pageable.getSort());

        Page<Client> response;
        response = clientService.filterBy(Example.of(clientConverter.convertDtoToModel(probe)), pageable);

        ClientsDTO clientsDTO = new ClientsDTO();
        clientsDTO.setPage(page);
        clientsDTO.setPagesCount(response.getTotalPages());
        clientsDTO.setClients(clientConverter.convertModelsToDtos(response.getContent()));

        return clientsDTO;
    }
}
