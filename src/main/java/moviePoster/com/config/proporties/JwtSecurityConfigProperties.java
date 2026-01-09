package moviePoster.com.config.proporties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ConfigurationProperties(prefix = "application.security.jwt")
public class JwtSecurityConfigProperties {
    // Это класс для хранения настроек JWT, которые берутся из application.yml

    private String secretKey;  // Это секретный ключ, которым подписывается JWT
    private AccessToken accessToken;
    private RefreshToken refreshToken;

    @Getter
    @Setter
    public static class AccessToken { // они не зависят от внешнего класса, Spring может спокойно их создать
        private Long expiration;
    }

    @Getter
    @Setter
    public static class RefreshToken {
        private Long expiration;
    }
}
