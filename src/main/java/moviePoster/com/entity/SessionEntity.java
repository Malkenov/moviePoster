package moviePoster.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "session")
public class SessionEntity extends BaseEntity {

    @Column(name = "start_time",nullable = false)
    private LocalDateTime startTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cinema_id")
    private CinemaEntity cinemas;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id")
    private MovieEntity movies;

    @OneToMany(mappedBy = "session")
    private List<TicketEntity> ticket;

    @OneToMany(mappedBy = "session")
    private List<SeatEntity> seat;

}
