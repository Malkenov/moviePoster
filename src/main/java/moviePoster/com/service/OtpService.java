package moviePoster.com.service;

import lombok.RequiredArgsConstructor;
import moviePoster.com.entity.OtpCode;
import moviePoster.com.repository.OtpRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final OtpRepository otpRepository;
    private final SmsService smsService;
    private final int MAX_ATTEMPTS = 5;

    public OtpCode generateOtpForPhone(String phone,String purpose){

        int code = generateRandomCode(); // генерирует случайный одноразовый код

        // Создание объекта OtpCode
        OtpCode otp = OtpCode.builder()
                .code(code)                                    // сам код
                .expiresAt(LocalDateTime.now().plusMinutes(5)) // срок действие кода - 5 минут
                .used(false)                                   // код еще не использован
                .isVerified(false)                             // код еще не подтвержден
                .channel("SMS")                                // канал отправки: смс, email
                .phone(phone)                                  // на какой номер отправить если он есть
                .purpose(purpose)                              // причина: регистрация, смена пароля, авторизация
                .attemptsAt(0)                                 // попытки ввода
                .build();

        otpRepository.save(otp); // сохранение объекта в БД
        smsService.sendSms(phone,"Ваш код" + code);

        return otp;
    }

    public int generateRandomCode(){
        SecureRandom secureRandom = new SecureRandom();
        return 10000 + secureRandom.nextInt(9000);
    }


    public boolean verifyOtp(String phone, Integer code, String purpose){

        OtpCode otp = otpRepository.findTopByPhoneAndPurposeOrderByCreatedAtDesc(phone, purpose)
                .orElseThrow(()-> new RuntimeException("OTP не найден"));


        if(otp.getIsVerified() || otp.getUsed()){
            throw new RuntimeException("Код уже был использован");
        }
        // Проверяет, если код был использован, то его нельзя использовать


        if(otp.getAttemptsAt() >= MAX_ATTEMPTS)
            throw new RuntimeException("Превышено количество попыток");
        // Если число попыток превышает лимит, выдается ошибка

        otp.setAttemptsAt(otp.getAttemptsAt() + 1);
        // Увеличивает количество выполненных попыток


        if(otp.getExpiresAt().isBefore(LocalDateTime.now())){
            otpRepository.save(otp);
            throw new RuntimeException("Код истёк");
        }
        // Проверяет срок действие кода, если превышен, выдает ошибку


        if(!otp.getCode().equals(code)){
            otpRepository.save(otp);
            throw new RuntimeException("Неверный код");
        }
        // Сравнивает веденный код с кодом в БД, при не совпадении ошибка


        otp.setIsVerified(true);
        otp.setUsed(true);


        otpRepository.save(otp);

        return true;
    }
}

