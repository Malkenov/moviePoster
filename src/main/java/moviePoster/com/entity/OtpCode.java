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
@Table(name = "otp_code")
public class OtpCode extends BaseEntity {

    @Column(name = "code")
    private int code;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "used", nullable = false)
    private Boolean used;

    @Column(name = "channel", nullable = false)
    private String channel;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "phone", nullable = true)
    private String phone;

    @Column(name = "purpose", nullable = false)
    private String purpose;

    @Column(name ="attempts_at", nullable = false)
    private int attemptsAt;

    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserSession user;

}
