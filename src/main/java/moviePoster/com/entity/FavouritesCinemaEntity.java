package moviePoster.com.entity;


import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "favourites_cinema")
public class FavouritesCinemaEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "cinema_id",unique = true)
    private CinemaEntity cinema;

    @ManyToOne
    @JoinColumn(name = "user_id",unique = true)
    private UserSessionEntity user;
}
