package provider;

import dto.MovieDTO;
import dto.MoviesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestMovieProvider implements MovieProvider {
    @Value("http://localhost:8080/api/movies")
    private String URL;

    private final RestTemplate restTemplate;

    @Autowired
    public RestMovieProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public MoviesDTO getAll() {
        return restTemplate.getForObject(
                URL,
                MoviesDTO.class
        );
    }

    @Override
    public MoviesDTO filterMoviesByName(String name) {
        return restTemplate.getForObject(
                URL + "/filter/name/" + name,
                MoviesDTO.class
        );
    }

    @Override
    public MoviesDTO filterMoviesByDescription(String descr) {
        return restTemplate.getForObject(
                URL + "/filter/description/" + descr,
                MoviesDTO.class
        );
    }

    @Override
    public MoviesDTO filterMoviesByPrice(int price) {
        return restTemplate.getForObject(
                URL + "/filter/price/" + price,
                MoviesDTO.class
        );
    }

    @Override
    public MoviesDTO filterMoviesByRating(int rating) {
        return restTemplate.getForObject(
                URL + "/filter/rating/" + rating,
                MoviesDTO.class
        );
    }

    @Override
    public MoviesDTO getAllSortedAscendingByFields(String fields) {
        return restTemplate.getForObject(
                URL + "/sorted/asc/" + fields,
                MoviesDTO.class
        );
    }

    @Override
    public MoviesDTO getAllSortedDescendingByFields(String fields) {
        return restTemplate.getForObject(
                URL + "/sorted/desc/" + fields,
                MoviesDTO.class
        );
    }

    @Override
    public void save(MovieDTO DTO) {
        restTemplate.postForObject(
                URL,
                DTO,
                MovieDTO.class);
    }

    @Override
    public void update(MovieDTO DTO) {
        restTemplate.put(
                URL + "/{id}",
                DTO,
                DTO.getId());
    }

    @Override
    public MovieDTO getByID(Long ID) {
        return restTemplate.getForObject(
                URL + "/" + ID,
                MovieDTO.class
        );
    }

    @Override
    public void delete(Long ID) {
        restTemplate.delete(
                URL + "/" + ID
        );
    }
}
