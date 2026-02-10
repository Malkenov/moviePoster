package moviePoster.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "genre")
public class Genre extends BaseEntity {

    @Column(name = "name",unique = true,nullable = false)
    private String name;


    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies = new HashSet<>();

}
