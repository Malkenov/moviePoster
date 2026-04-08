package moviePoster.com.entity;


import jakarta.persistence.*;
import lombok.*;
import moviePoster.com.enums.SeatStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "seat")
public class SeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    @Column
    private LocalDateTime reservedUntil;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserSessionEntity user;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private SessionEntity sessionEntity;

}

