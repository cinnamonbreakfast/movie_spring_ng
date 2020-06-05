package service;

import domain.Client;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

public interface ClientService extends BaseService<Long, Client> {
    Client addClient(Client entity);
    List<Client> filterBy(Optional<String> firstName, Optional<String> secondName, Optional<String> job, Optional<Integer> age);
    Page<Client> filterBy(Example<Client> example, Pageable pageRequest);
    void removeClient(Long id);
    void updateClient(Client entity);
    Page<Client> findAll(Pageable pageable);
}
