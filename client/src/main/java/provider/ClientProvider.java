package provider;

import dto.ClientDTO;
import dto.ClientsDTO;

public interface ClientProvider extends Provider<ClientDTO> {
    ClientsDTO getAll();
    ClientsDTO filterClientsByFirstName(String first);
    ClientsDTO filterClientsBySecondName(String second);
    ClientsDTO filterClientsByAge(int age);
    ClientsDTO getAllSortedAscendingByFields(String fields);
    ClientsDTO getAllSortedDescendingByFields(String fields);
}
