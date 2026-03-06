package moviePoster.com.entity;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "session")
public class SessionEntity extends BaseEntity {

    @Column(name = "start_time",nullable = false)
    private Integer startTime;


    @ManyToOne(optional = false)
    @JoinColumn(name = "cinema_id")
    private CinemaEntity cinemas;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id")
    private MovieEntity movies;

    @OneToOne(mappedBy = "session")
    private TicketEntity ticket;

}
