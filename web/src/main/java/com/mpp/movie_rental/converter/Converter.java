package com.mpp.movie_rental.converter;

import domain.Entity;
import com.mpp.movie_rental.dto.BaseDTO;

public interface Converter<Model extends Entity<Long>, Dto extends BaseDTO> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}
