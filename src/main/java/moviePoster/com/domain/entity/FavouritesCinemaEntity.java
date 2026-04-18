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
@Table(name = "favourites_cinema", uniqueConstraints = @UniqueConstraint(columnNames = {"cinema_id", "user_id"}))
public class FavouritesCinemaEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private CinemaEntity cinema;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserSessionEntity user;
}
