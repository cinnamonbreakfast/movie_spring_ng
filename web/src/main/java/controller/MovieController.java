package controller;

import converter.MovieConverter;
import dto.MovieDTO;
import dto.MoviesDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.MovieService;

import java.util.List;

@RestController
public class MovieController {
    public static final Logger log= LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieConverter movieConverter;

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    MoviesDTO getMovies() {
        log.trace("getMovies - method entered");
        return new MoviesDTO(movieConverter.convertModelsToDtos(movieService.getAll()));
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    void saveMovie(@RequestBody MovieDTO movieDTO) {
        log.trace("saveMovie - method entered: movieDTO={}", movieDTO);
        movieService.addMovie(
                movieConverter.convertDtoToModel(movieDTO)
        );
        log.trace("saveMovie - method finished");
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT)
    void updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        log.trace("updateMovie - method entered: id={}, movieDTO={}", id, movieDTO);

        movieDTO.setId(id);
        movieService.updateMovie(
                movieConverter.convertDtoToModel(movieDTO)
        );

        log.trace("updateMovie - method finished");
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.GET)
    MovieDTO getByID(@PathVariable Long id) {
        log.trace("getByID - method entered: id={}", id);

        return movieConverter.convertModelToDto(
                movieService.getByID(id)
        );
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteMovie(@PathVariable Long id){
        log.trace("deleteMovie - method entered: id={}", id);

        movieService.removeMovie(id);

        log.trace("deleteMovie - method finished");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/movies/filter/name/{name}", method = RequestMethod.GET)
    MoviesDTO filterMovieByName(@PathVariable String name)
    {
        log.trace("filterMovieByName - method entered: name={}", name);

        return new MoviesDTO(
                movieConverter.convertModelsToDtos(
                        movieService.filterMovieByName(name)
                )
        );
    }

    @RequestMapping(value = "/movies/filter/description/{description}", method = RequestMethod.GET)
    MoviesDTO filterMovieByDescription(@PathVariable String description)
    {
        log.trace("filterMovieByDescription - method entered: description={}", description);

        return new MoviesDTO(
                movieConverter.convertModelsToDtos(
                        movieService.filterMovieByDescription(description)
                )
        );
    }

    @RequestMapping(value = "/movies/filter/price/{price}", method = RequestMethod.GET)
    MoviesDTO filterMovieByPrice(@PathVariable int price)
    {
        log.trace("filterMovieByPrice - method entered: price={}", price);
        return new MoviesDTO(
                movieConverter.convertModelsToDtos(
                        movieService.filterMovieByPrice(price)
                )
        );
    }
    @RequestMapping(value = "/movies/filter/rating/{rating}", method = RequestMethod.GET)
    MoviesDTO filterMovieByRating(@PathVariable int rating)
    {
        log.trace("filterMovieByRating - method entered: rating={}", rating);
        return new MoviesDTO(
                movieConverter.convertModelsToDtos(
                        movieService.filterMovieByRating(rating)
                )
        );
    }


    @RequestMapping(value = "/movies/sorted/asc/{fields}", method = RequestMethod.GET)
    MoviesDTO getAllSortedAscendingByFields(@PathVariable String fields)
    {
        log.trace("getAllSortedAscendingByFields - method entered: fields={}", fields);
        return new MoviesDTO(
                movieConverter.convertModelsToDtos(
                        movieService.getAllSortedAscendingByFields(fields)
                )
        );
    }

    @RequestMapping(value = "/movies/sorted/desc/{fields}", method = RequestMethod.GET)
    MoviesDTO getAllSortedDescendingByFields(@PathVariable String fields)
    {
        log.trace("getAllSortedDescendingByFields - method entered: fields={}", fields);
        return new MoviesDTO(
                movieConverter.convertModelsToDtos(
                        movieService.getAllSortedDescendingByFields(fields)
                )
        );
    }
}
