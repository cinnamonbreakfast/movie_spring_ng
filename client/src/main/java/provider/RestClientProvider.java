package provider;

import dto.ClientDTO;
import dto.ClientsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClientProvider implements ClientProvider {
    @Value("http://localhost:8080/api/clients")
    private String URL;

    private final RestTemplate restTemplate;

    @Autowired
    public RestClientProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public ClientsDTO filterClientsByFirstName(String first) {
        return restTemplate.getForObject(
                URL + "/filter/firstName/" + first,
                ClientsDTO.class
        );
    }

    @Override
    public ClientsDTO filterClientsBySecondName(String second) {
        return restTemplate.getForObject(
                URL + "/filter/secondName/" + second,
                ClientsDTO.class
        );
    }

    @Override
    public ClientsDTO filterClientsByAge(int age) {
        return restTemplate.getForObject(
                URL + "/filter/age/" + age,
                ClientsDTO.class
        );
    }

    @Override
    public ClientsDTO getAllSortedAscendingByFields(String fields) {
        return restTemplate.getForObject(
                URL + "/sorted/asc/" + fields,
                ClientsDTO.class
        );
    }

    @Override
    public ClientsDTO getAllSortedDescendingByFields(String fields) {
        return restTemplate.getForObject(
                URL + "/sorted/desc/" + fields,
                ClientsDTO.class
        );
    }

    public ClientsDTO getAll() {
        return restTemplate.getForObject(
                URL,
                ClientsDTO.class
        );
    }

    @Override
    public void save(ClientDTO DTO) {
        restTemplate.postForObject(
                URL,
                DTO,
                ClientDTO.class);
    }

    @Override
    public void update(ClientDTO DTO) {
        restTemplate.put(
                URL + "/{id}",
                DTO,
                DTO.getId());
    }

    @Override
    public ClientDTO getByID(Long ID) {
        return restTemplate.getForObject(
                URL + "/" + ID,
                ClientDTO.class
        );
    }

    @Override
    public void delete(Long ID) {
        restTemplate.delete(
                URL + "/" + ID
        );
    }
}
