package service;

import domain.Client;

import java.util.List;

public interface ClientService extends BaseService<Long, Client> {
    void addClient(Client entity);
    List<Client> getAllClients(String... sort);
    List<Client> filterClientsByFirstName(String name);
    List<Client> filterClientsByLastName(String name);
    List<Client> filterClientsByAge(int age);
    void removeClient(Long id);
    void updateClient(Client entity);
}
