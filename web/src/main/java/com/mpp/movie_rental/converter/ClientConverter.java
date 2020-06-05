package com.mpp.movie_rental.converter;

import domain.Client;
import com.mpp.movie_rental.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDTO> {
    @Autowired
    MovieConverter movieConverter;

    @Override
    public Client convertDtoToModel(ClientDTO dto) {
        Client client = Client.builder()
                .firstName(dto.getFirstName())
                .secondName(dto.getSecondName())
                .age(dto.getAge())
                .job(dto.getJob())
                .build();
        client.setId(dto.getId());
        return client;
    }

    @Override
    public ClientDTO convertModelToDto(Client client) {
        ClientDTO dto = ClientDTO.builder()
                .firstName(client.getFirstName())
                .secondName(client.getSecondName())
                .age(client.getAge())
                .job(client.getJob())
                .movieDTOList(movieConverter.convertModelsToDtos(client.getMovies()))
                .build();
        dto.setId(client.getId());
        return dto;
    }
}
