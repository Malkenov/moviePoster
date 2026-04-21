package moviePoster.com.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "session")
public class SessionEntity extends BaseEntity {

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private CinemaEntity cinemas;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movies;

    @OneToMany(mappedBy = "session")
    @Builder.Default
    private List<TicketEntity> ticket = new ArrayList<>();

    @OneToMany(mappedBy = "sessionEntity")
    @Builder.Default
    private List<SeatEntity> seat = new ArrayList<>();
}
