package moviePoster.com.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "genre")
public class GenreEntity extends BaseEntity {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "genres")
    @Builder.Default
    private Set<MovieEntity> movies = new HashSet<>();
}
