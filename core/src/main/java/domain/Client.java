package domain;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Client extends domain.Entity<Long> {
    @Basic(optional = false)
    @Column(nullable = false)
    private String firstName;

    @Basic(optional = false)
    @Column(nullable = false)
    private String secondName;

    @Basic(optional = false)
    @Column(nullable = false)
    private String job;

    @Basic(optional = false)
    @Column(nullable = false)
    private Integer age;

    @ManyToMany
    @JoinTable(
            name = "rental",
            joinColumns = @JoinColumn(name = "client"),
            inverseJoinColumns = @JoinColumn(name = "movie"))
    List<Movie> movies;
}