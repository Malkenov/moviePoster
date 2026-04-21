package moviePoster.com.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "auth_sessions")
public class AuthSessions extends BaseEntity {

    @Column(name = "refresh_token", unique = true)
    private String refreshToken;

    @Column(name = "expires_at", nullable = false)
    private Double expiresAt;

    @Column(name = "revoked")
    @Builder.Default
    private Boolean revoked = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserSessionEntity user;
}
