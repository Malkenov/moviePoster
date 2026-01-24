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
@Table(name = "Билеты")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Ряд",unique = true,nullable = false)
    @Positive
    private int row;

    @Column(name = "Место",unique = true,nullable = false)
    @Positive
    private int place;


    @Column(name = "Создание", updatable = false)
    private LocalDateTime created_at;

    @Column(name = "Обновление")
    private LocalDateTime modified_at;


    // автоматический устанавливается при создании
    @PrePersist
    protected void onCreate(){
        this.created_at = LocalDateTime.now();
        this.modified_at = LocalDateTime.now();
    }

    // срабатывает обновление записи
    @PreUpdate
    protected void onUpdate(){
        this.modified_at = LocalDateTime.now();
    }


    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "session_id",unique = true)
    private Session session;

    @OneToMany(mappedBy = "ticket")
    private List<Price> price;
}
