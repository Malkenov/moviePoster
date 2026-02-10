package moviePoster.com.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "favourites_cinema")
public class FavouritesCinema extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "cinema_id",unique = true)
    private Cinema cinema;

    @ManyToOne
    @JoinColumn(name = "user_id",unique = true)
    private UserSession user;
}
