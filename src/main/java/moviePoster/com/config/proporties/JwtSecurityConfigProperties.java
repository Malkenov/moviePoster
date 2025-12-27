package moviePoster.com.config.proporties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.security.jwt")
public class JwtSecurityConfigProperties {

    private String secretKey;
    private AccessToken accessToken;
    private RefreshToken refreshToken;

    @Getter
    @Setter
    public static class AccessToken {
        private Long expiration;
    }

    @Getter
    @Setter
    public static class RefreshToken {
        private Long expiration;
    }
}
