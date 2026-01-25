package moviePoster.com.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "row",unique = true,nullable = false)
    @Positive
    private int row;

    @Column(name = "place",unique = true,nullable = false)
    @Positive
    private int place;


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


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserSession user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "session_id",unique = true)
    private Session session;

    @OneToMany(mappedBy = "ticket")
    private List<Price> price;
}
