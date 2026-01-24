package moviePoster.com.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Залы")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Название зала",unique = true)
    private String name;

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
    @JoinColumn(name = "cinema_id",unique = true)
    private Cinema cinema;
}
