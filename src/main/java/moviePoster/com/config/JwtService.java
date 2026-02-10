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



    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecurityConfigProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
    // Метод возвращает секретный ключ, который подписывается токен при регистрации и авторизации


    /* --------------------------------------------------------------------------------*/


    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey()) // ключ, которым подписан токен
                .build()
                .parseClaimsJws(token) // проверка подписи
                .getBody();            // возвращает claims
    }
    // Проверяет подпись токена, а так же его расшифровывает и возвращает все его поля



    /* --------------------------------------------------------------------------------*/



    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    // Метод с помощью токена, берет его же нужные поля



    /* --------------------------------------------------------------------------------*/


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    // Метод берет токен и с него достает пользователя по email


    /* --------------------------------------------------------------------------------*/


    private String buildToken(Map<String, Object> claims,
                              UserDetails userDetails, Long expiration) {
        // claims - доп поля, которые хочешь заполнить(id,role,phone)
        // userDetails - содержит email, пароль, роли
        // expiration - время жизни токена

        return Jwts.builder() // создаёт объект, который будет собирать токен шаг за шагом
                .setClaims(claims) // Добавляем дополнительные поля, если claims пустой — ничего страшного
                .setSubject(userDetails.getUsername())             // email пользователя
                .setIssuedAt(new Date(System.currentTimeMillis())) // когда токен создан
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // когда токен тухнет
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // подписываем токен секретным ключом
                .compact();                                         // превращаем всё в строку (токен)
    }
    // Создание токена


    /* --------------------------------------------------------------------------------*/


    public String generateAccessToken(UserDetails userDetails) {
        return generateAccessToken(new HashMap<>(), userDetails);
    }



    public String generateAccessToken(Map<String, Object> claims, // claims дополнительные данные, которые будут записаны в токен
                                      UserDetails userDetails) {  // userDetails объект пользователя (обычно содержит email, пароль, роли)
        Long accessTokenExpiration = jwtSecurityConfigProperties
                .getAccessToken().
                getExpiration(); // Получаем время жизни access-токена

        return buildToken(claims, userDetails, accessTokenExpiration); // вызывает общий метод сборки токена, и передаёт время жизни access
        // создает Access Token для пользователя
    }



    public String generateRefreshToken(UserDetails userDetails) {
        return generateRefreshToken(new HashMap<>(), userDetails);
    }



    public String generateRefreshToken(Map<String, Object> claims, // claims доп данные который записываются в токен
                                       UserDetails userDetails) {  // userDetails объект пользователя
        Long accessTokenExpiration = jwtSecurityConfigProperties.getRefreshToken().getExpiration(); // тут вызывается время жизни refresh токена

        return buildToken(claims, userDetails, accessTokenExpiration); // вызывает общий метод сборки токена
        // создает Refresh Token для пользователя
    }


    /* --------------------------------------------------------------------------------*/


    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token); // Из токена достается email/username
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
             // тут сравнивается username, принадлежит ли он ему, а тут токен НЕ должен быть просрочен
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    // метод берет дату истечение токена, и сверяет с настоящим временем

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    // метод из токена достаёт поле exp, и возвращает виде Data


}

