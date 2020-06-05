package com.mpp.movie_rental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientsDTO {
    private List<ClientDTO> clients;
    private Integer page;
    private Integer pagesCount;
}
