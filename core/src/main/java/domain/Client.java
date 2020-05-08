package domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Client extends domain.Entity<Long> {
    private String firstName;
    private String secondName;
    private String job;
    private int age;
}