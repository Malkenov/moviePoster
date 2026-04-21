package moviePoster.com.domain.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import moviePoster.com.dto.enums.SeatStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "seat")
public class SeatEntity extends BaseEntity {

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

