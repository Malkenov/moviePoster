package moviePoster.com.controller;

import lombok.RequiredArgsConstructor;
import moviePoster.com.service.OtpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/otp")
public class OtpController {

    private final OtpService otpService;

    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(@RequestParam String phone){
        otpService.generateOtp(phone, "LOGIN");
        return ResponseEntity.ok("Код отправлен");
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestParam String phone,
                                            @RequestParam Integer code){
        otpService.verifyOtp(phone,code,"LOGIN");
        return ResponseEntity.ok("Код подтвержден");
    }
}
