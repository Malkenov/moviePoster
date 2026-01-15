package moviePoster.com.controller;

import lombok.RequiredArgsConstructor;
import moviePoster.com.authService.AuthenticationService;
import moviePoster.com.authService.LogoutService;
import moviePoster.com.dto.request.AuthenticationRequestDto;
import moviePoster.com.dto.request.LogoutRequest;
import moviePoster.com.dto.request.RefreshTokenRequest;
import moviePoster.com.dto.request.RegistrationRequestDto;
import moviePoster.com.dto.response.AuthenticationResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final LogoutService logoutService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody RegistrationRequestDto dto) {
        return ResponseEntity.ok(authenticationService.register(dto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequestDto request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<AuthenticationResponseDto> refresh(
            @RequestBody RefreshTokenRequest request
    ) {
        return ResponseEntity.ok(
                authenticationService.refresh(request.getRefreshToken())
        );
    }

   // @PostMapping("/logout")
   //public ResponseEntity<Void> logout(@RequestBody LogoutRequest request) {
   //     logoutService.logout(request.getRefreshToken());
   // }

}

