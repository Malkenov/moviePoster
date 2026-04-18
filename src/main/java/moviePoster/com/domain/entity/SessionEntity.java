package moviePoster.com.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "session")
public class SessionEntity extends BaseEntity {

    @Column(name = "start_time",nullable = false)
    private LocalDateTime startTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cinema_id")
    private CinemaEntity cinema;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    @OneToMany(mappedBy = "session")
    private List<TicketEntity> ticket;

    @OneToMany(mappedBy = "sessionEntity")
    private List<SeatEntity> seat;

}
