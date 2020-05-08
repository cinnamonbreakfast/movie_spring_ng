package provider;

import dto.MoviesDTO;
import dto.MovieDTO;

public interface MovieProvider extends Provider<MovieDTO> {
    MoviesDTO getAll();
    MoviesDTO filterMoviesByName(String name);
    MoviesDTO filterMoviesByDescription(String descr);
    MoviesDTO filterMoviesByPrice(int price);
    MoviesDTO filterMoviesByRating(int rating);
    MoviesDTO getAllSortedAscendingByFields(String fields);
    MoviesDTO getAllSortedDescendingByFields(String fields);
}
