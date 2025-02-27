package service;


import domain.Client;
import domain.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ClientRepository;

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
    public void addClient(Client entity) {
        log.trace("addClient - method entered: student={}", entity);
        clientRepository.save(entity);
        log.trace("addClient - method finished");
    }

    @Override
    public List<Client> getAllClients(String... sort) {
        log.trace("getAllClients - method entered: sort={}", (Object[]) sort);
        Iterable<Client> clients = clientRepository.findAll(new Sort(Sort.Direction.ASC, sort));
        log.trace("getAllClients - method finished");
        return StreamSupport.stream(
                clients.spliterator(),
                false)
                .collect(Collectors.toList());

    }

    @Override
    public List<Client> filterClientsByFirstName(String name) {
        log.trace("filterClientsByFirstName - method entered: name={}", name);
        Iterable<Client> clients = clientRepository.findAll();
        log.trace("filterClientsByFirstName - method finished");
        return StreamSupport.stream(
                clients.spliterator(),
                false)
                .filter((e)-> e.getFirstName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> filterClientsByLastName(String name) {
        log.trace("filterClientsByLastName - method entered: name={}", name);
        Iterable<Client> clients = clientRepository.findAll();
        log.trace("filterClientsByLastName - method finished");
        return StreamSupport.stream(
                clients.spliterator(),
                false)
                .filter((e)-> e.getSecondName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> filterClientsByAge(int age) {
        log.trace("filterClientsByAge - method entered: age={}", age);
        Iterable<Client> clients = clientRepository.findAll();
        log.trace("filterClientsByAge - method finished");
        return StreamSupport.stream(
                clients.spliterator(),
                false)
                .filter((e)->e.getAge()==age)
                .collect(Collectors.toList());
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
    public List<Client> getAllSortedAscendingByFields(String fields) {
        log.trace("getAllSortedAscendingByFields - method entered: fields={}", fields);
        Sort sort = new Sort(Sort.Direction.ASC, fields);
        List<Client> clients = clientRepository.findAll(sort);
        log.trace("getAllSortedAscendingByFields - method finished");
        return clients;
    }

    @Override
    public List<Client> getAllSortedDescendingByFields(String fields) {
        log.trace("getAllSortedDescendingByFields - method entered: fields={}", fields);
        Sort sort = new Sort(Sort.Direction.DESC, fields);
        List<Client> clients = clientRepository.findAll(sort);
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

    public List<Client> filterBy(Optional<String> firstName, Optional<String> secondName, Optional<String> job, Optional<Integer> age)
    {
        Client client = new Client();

        firstName.ifPresent(client::setFirstName);
        secondName.ifPresent(client::setSecondName);
        job.ifPresent(client::setJob);
        age.ifPresent(client::setAge);

        return clientRepository.findAll(Example.of(client));
    }
}
