package moviePoster.com.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Otp_codes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int code;

    @Column(name = "Время истечения")
    private LocalTime expires_at;

    @Column(nullable = false)
    private Boolean used;

    @Column(nullable = false)
    private String channel;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = false)
    private String purpose;

    @Column(nullable = false)
    private int attempts_at;

    @Column(nullable = false)
    private Boolean is_verified;

    @Column(name = "Создание", updatable = false)
    private LocalDateTime created_at;

    @Column(name = "Обновление",nullable = false)
    private LocalDateTime modified_at;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;


    @PrePersist
    protected void onCreate(){
        this.created_at = LocalDateTime.now();
        this.modified_at = LocalDateTime.now();
    }

    @PrePersist
    protected void onUpdate(){
        this.modified_at = LocalDateTime.now();
    }
}
