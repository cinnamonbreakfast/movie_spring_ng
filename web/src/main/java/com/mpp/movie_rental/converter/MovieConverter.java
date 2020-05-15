package com.mpp.movie_rental.converter;

import domain.Client;
import domain.Movie;
import com.mpp.movie_rental.dto.ClientDTO;
import com.mpp.movie_rental.dto.MovieDTO;
import org.springframework.stereotype.Component;

@Component
public class MovieConverter extends BaseConverter<Movie, MovieDTO> {
    @Override
    public Movie convertDtoToModel(MovieDTO dto) {
        Movie movie = Movie.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .rating(dto.getRating())
                .price(dto.getPrice())
                .build();
        movie.setId(dto.getId());
        return movie;
    }

    @Override
    public MovieDTO convertModelToDto(Movie movie) {
        MovieDTO dto = MovieDTO.builder()
                .name(movie.getName())
                .description(movie.getDescription())
                .price(movie.getPrice())
                .rating(movie.getRating())
                .build();
        dto.setId(movie.getId());
        return dto;
    }
}
