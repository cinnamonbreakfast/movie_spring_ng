package provider;

import dto.MovieDTO;
import dto.MoviesDTO;
import dto.RentalDTO;
import dto.RentalsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestRentalProvider implements RentalProvider {
    @Value("http://localhost:8080/api/rentals")
    private String URL;

    private final RestTemplate restTemplate;

    @Autowired
    public RestRentalProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public RentalsDTO getAll() {
        return restTemplate.getForObject(
                URL,
                RentalsDTO.class
        );
    }

    @Override
    public RentalsDTO getAllSortedAscendingByFields(String fields) {
        return restTemplate.getForObject(
                URL + "/sorted/asc/" + fields,
                RentalsDTO.class
        );
    }

    @Override
    public RentalsDTO getAllSortedDescendingByFields(String fields) {
        return restTemplate.getForObject(
                URL + "/sorted/asc/" + fields,
                RentalsDTO.class
        );
    }

    @Override
    public void save(RentalDTO DTO) {
        restTemplate.postForObject(
                URL,
                DTO,
                RentalDTO.class);
    }

    @Override
    public void update(RentalDTO DTO) {
        restTemplate.put(
                URL + "/{id}",
                DTO,
                DTO.getId());
    }

    @Override
    public RentalDTO getByID(Long ID) {
        return restTemplate.getForObject(
                URL + "/" + ID,
                RentalDTO.class
        );
    }

    @Override
    public void delete(Long ID) {
        restTemplate.delete(
                URL + "/" + ID
        );
    }
}
