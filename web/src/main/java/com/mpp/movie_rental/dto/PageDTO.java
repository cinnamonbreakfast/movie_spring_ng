package com.mpp.movie_rental.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
@ToString()
@Builder
public class PageDTO implements Serializable {
    private int page;
    private int size;
}
