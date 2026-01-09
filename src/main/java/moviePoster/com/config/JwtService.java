package moviePoster.com.config;

import io.jsonwebtoken.SignatureAlgorithm;
import moviePoster.com.config.proporties.JwtSecurityConfigProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtService {
    // Создать и проверить токен

    private final JwtSecurityConfigProperties jwtSecurityConfigProperties;
    // Здесь приходят настройки из application.yml

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String generateAccessToken(UserDetails userDetails) {
        return generateAccessToken(new HashMap<>(), userDetails);
    }

    public String generateAccessToken(Map<String, Object> claims,
                                      UserDetails userDetails) {
        Long accessTokenExpiration = jwtSecurityConfigProperties.getAccessToken().getExpiration();

        return buildToken(claims, userDetails, accessTokenExpiration);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return generateRefreshToken(new HashMap<>(), userDetails);
    }

    public String generateRefreshToken(Map<String, Object> claims,
                                       UserDetails userDetails) {
        Long accessTokenExpiration = jwtSecurityConfigProperties.getRefreshToken().getExpiration();

        return buildToken(claims, userDetails, accessTokenExpiration);
    }

    private String buildToken(Map<String, Object> claims,
                              UserDetails userDetails, Long expiration) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())             // email пользователя
                .setIssuedAt(new Date(System.currentTimeMillis())) // когда токен создан
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // когда токен тухнет
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // подписываем токен секретным ключом
                .compact();                                         // превращаем всё в строку
    }

    // Проверяет - токен не протух
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey()) // ключ
                .build()
                .parseClaimsJws(token) // проверка подписи
                .getBody();            // claims
    }


    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecurityConfigProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

