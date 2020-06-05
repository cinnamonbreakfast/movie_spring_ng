package com.mpp.movie_rental.dto;

import javafx.util.Pair;
import lombok.*;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
@ToString()
@Builder
public class SortDTO {
    private Map<String, String> fields;
}
