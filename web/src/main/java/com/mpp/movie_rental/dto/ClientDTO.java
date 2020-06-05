package com.mpp.movie_rental.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class ClientDTO extends BaseDTO {
    private String firstName;
    private String secondName;
    private String job;
    private Integer age;
    private List<MovieDTO> movieDTOList;
}
