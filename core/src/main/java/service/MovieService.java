package service;

import domain.Movie;

import java.io.IOException;
import java.util.List;

public interface MovieService extends BaseService<Long, Movie> {
    void addMovie(Movie movie);
    List<Movie> getAllMovies(String... sort);
    List<Movie> filterMovieByName(String s);
    List<Movie> filterMovieByDescription(String d);
    List<Movie> filterMovieByPrice(int p);
    List<Movie> filterMovieByRating(int r);
    void removeMovie(Long id);
    void updateMovie(Movie entity);
}
