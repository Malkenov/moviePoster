package moviePoster.com.domain.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "hall")
public class HallEntity extends BaseEntity {

    @Column(name = "name",unique = true)
    private String name;


    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private CinemaEntity cinema;
}
