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
@Table(name = "auth_sessions")
public class AuthSessions extends BaseEntity {

    @Column(name = "refresh_token",unique = true)
    private String refreshToken;

    @Column(name = "expires_at",nullable = false)
    private Double expiresAt;

    @Column(name = "revoked")
    private Boolean revoked = false;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserSession user;
}
