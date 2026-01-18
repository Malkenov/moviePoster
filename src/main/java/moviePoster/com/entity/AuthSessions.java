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
@Table
public class AuthSessions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Токен рефреш")
    private String refresh_token;

    @Column(name = "Время истечения")
    private Double expires_at;

    @Column(name = "Логаут")
    private Boolean revoked;

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
    @PrePersist
    protected void onUpdate(){
        this.modified_at = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
