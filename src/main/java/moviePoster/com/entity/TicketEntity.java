package moviePoster.com.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import moviePoster.com.enums.SeatStatus;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ticket", uniqueConstraints = @UniqueConstraint(columnNames = {"session_id", "row", "place"})) //для избежании покупки одинаковых мест
public class TicketEntity extends BaseEntity {

    @Column(name = "row",nullable = false)
    @Positive
    private int row;   // ряд

    @Column(name = "place",nullable = false)
    @Positive
    private int place; // место

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserSessionEntity user;

    @ManyToOne
    @JoinColumn(name = "session_id",unique = true)
    private SessionEntity session;

    @OneToMany(mappedBy = "ticket")
    private List<PriceEntity> price;
}
