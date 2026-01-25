package moviePoster.com.authService;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import moviePoster.com.dto.request.RegistrationRequestDto;
import moviePoster.com.dto.request.AuthenticationRequestDto;
import moviePoster.com.dto.response.AuthenticationResponseDto;
import moviePoster.com.entity.Token.TokenEntity;
import moviePoster.com.entity.UserSession;
import moviePoster.com.enums.Role;
import moviePoster.com.config.JwtService;
import moviePoster.com.enums.TokenType;
import moviePoster.com.repository.TokenRepository;
import moviePoster.com.repository.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDto register(RegistrationRequestDto dto) {
        var user = UserSession.builder()
                .name(dto.getName())
                .dateOfBirthday(dto.getDateOfBirthday())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        var jwtAccessToken = jwtService.generateAccessToken(user);
        var jwtRefreshToken = jwtService.generateRefreshToken(user);

        return AuthenticationResponseDto.builder()
                .accessToken(jwtAccessToken)
                .refreshToken(jwtRefreshToken)
                .build();
    }

   //-------------------------------------------------------------------------------

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword())
        );

        var user = userRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new UsernameNotFoundException("User not found"));

        var jwtAccessToken = jwtService.generateAccessToken(user);
        var jwtRefreshToken = jwtService.generateRefreshToken(user);

        return AuthenticationResponseDto.builder()
                .accessToken(jwtAccessToken)
                .refreshToken(jwtRefreshToken)
                .build();
    }


    //-------------------------------------------------------------------------------


    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeaderValue = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwtRefreshToken;
        final String userEmail;

        if (authHeaderValue == null || !authHeaderValue.startsWith("Bearer ")) {
            return;
        }

        jwtRefreshToken = authHeaderValue.substring(7);
        userEmail = jwtService.extractUsername(jwtRefreshToken);
        if (userEmail != null) {
            var users = userRepository.findByEmail(userEmail).orElseThrow(
                    () -> new UsernameNotFoundException("User not found"));

            if (jwtService.isTokenValid(jwtRefreshToken, users)) {
                var jwtAccessToken = jwtService.generateAccessToken(users);
                revokeAllUserTokens(users);
                saveUserToken(users, jwtAccessToken);

                var authResponse = AuthenticationResponseDto.builder()
                        .accessToken(jwtAccessToken)
                        .refreshToken(jwtRefreshToken)
                        .build();

                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }


    //-------------------------------------------------------------------------------


    private void saveUserToken(UserSession users, String jwtToken) {
        var tokenEntity = TokenEntity.builder()
                .users(users)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();

        tokenRepository.save(tokenEntity);
    }


    //-------------------------------------------------------------------------------


    private void revokeAllUserTokens(UserSession users) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(users.getId());

        if (validUserTokens.isEmpty()) {
            return;
        }

        validUserTokens.forEach(tokenEntity -> {
            tokenEntity.setRevoked(true);
            tokenEntity.setExpired(true);
        });

        tokenRepository.saveAll(validUserTokens);
    }


    //-------------------------------------------------------------------------------


    public AuthenticationResponseDto refresh(String refreshToken) {

        TokenEntity tokenEntity = tokenRepository
                .findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (tokenEntity.isExpired() || tokenEntity.isRevoked()) {
            throw new RuntimeException("Refresh token is expired or revoked");
        }

        UserSession users = tokenEntity.getUsers();

        String newAccessToken = jwtService.generateRefreshToken(users);

        return new AuthenticationResponseDto(
                newAccessToken,
                tokenEntity.getToken()
        );
    }


}
