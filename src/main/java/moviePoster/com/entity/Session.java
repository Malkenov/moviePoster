package moviePoster.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time",nullable = false)
    private Integer startTime;


    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;


    // автоматический устанавливается при создании
    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    // срабатывает обновление записи
    @PreUpdate
    protected void onUpdate(){
    this.modifiedAt = LocalDateTime.now();
    }



    @ManyToOne(optional = false)
    @JoinColumn(name = "cinema_id")
    private Cinema cinemas;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id")
    private Movie movies;

    @OneToOne(mappedBy = "session")
    private Ticket ticket;

}
