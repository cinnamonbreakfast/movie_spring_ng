package dto;

import lombok.*;

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
    private int age;
}
