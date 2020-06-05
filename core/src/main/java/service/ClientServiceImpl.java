package service;


import domain.Client;
import domain.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ClientRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientServiceImpl implements ClientService {
    public static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client addClient(Client entity) {
        log.trace("addClient - method entered: student={}", entity);
        return clientRepository.save(entity);
    }

    @Override
    public void removeClient(Long id) {
        log.trace("removeClient - method entered: id={}", id);
        clientRepository.deleteById(id);
        log.trace("removeClient - method finished");
    }


    @Override
    @Transactional
    public void updateClient(Client entity) {
        log.trace("updateClient - method entered: entity={}", entity);
        clientRepository.findById(entity.getId())
                .ifPresent(s -> {
                    s.setFirstName(entity.getFirstName());
                    s.setSecondName(entity.getSecondName());
                    s.setJob(entity.getJob());
                    s.setAge(entity.getAge());
                });
        log.trace("updateClient - method finished");
    }

    @Override
    public List<Client> getAll() {
        log.trace("getAll - method entered");
        Iterable<Client> clients = clientRepository.findAll();
        log.trace("getAll - method finished");
        return StreamSupport.stream(
                clients.spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> getAllSortedAscendingByFields(String[] fields) {
        log.trace("getAllSortedAscendingByFields - method entered: fields={}", (Object) fields);
        List<Client> clients = clientRepository.findAll(Sort.by(fields).ascending());
        log.trace("getAllSortedAscendingByFields - method finished");
        return clients;
    }

    @Override
    public List<Client> getAllSortedDescendingByFields(String[] fields) {
        log.trace("getAllSortedDescendingByFields - method entered: fields={}", (Object) fields);
        List<Client> clients = clientRepository.findAll(Sort.by(fields).ascending());
        log.trace("getAllSortedDescendingByFields - method finished");
        return clients;
    }

    @Override
    public Client getByID(Long aLong) {
        log.trace("getById - method entered: aLong={}", aLong);
        Optional<Client> client = clientRepository.findById(aLong);
        if(client.isPresent()) {
            log.trace("getById - method finished");
            return client.get();
        }
        throw new RuntimeException("Could not find client by given ID.");
    }

    public List<Client> filterBy(Client example)
    {
        log.trace("filterBy - method entered: example={}", example);
        return clientRepository.findAll(Example.of(example));
    }

    @Override
    public List<Client> filterBy(Optional<String> firstName, Optional<String> secondName, Optional<String> job, Optional<Integer> age)
    {
        Client client = new Client();

        firstName.ifPresent(client::setFirstName);
        secondName.ifPresent(client::setSecondName);
        job.ifPresent(client::setJob);
        age.ifPresent(client::setAge);

        return clientRepository.findAll(Example.of(client));
    }

    @Override
    public Page<Client> filterBy(Example<Client> matcher, Pageable pageRequest)
    {
        return clientRepository.findAll(matcher, pageRequest);
    }

    @Override
    public Page<Client> findAll(Pageable pageRequest) {
        log.trace("findAll - method entered: pageRequest={}", pageRequest);

        return clientRepository.findAll(pageRequest);
    }
}
