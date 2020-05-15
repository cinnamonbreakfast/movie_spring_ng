package com.mpp.movie_rental.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BaseDTO implements Serializable {
    private Long Id;
}
