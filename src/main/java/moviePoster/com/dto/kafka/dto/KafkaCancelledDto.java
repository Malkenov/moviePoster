package moviePoster.com.dto.kafka.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaCancelledDto {

    @NotBlank(message = "Id билета не может быть пустым!")
    private String ticketId;           // номер билета

    @NotNull(message = "Id пользователя не может быть пустым!")
    @Positive(message = "Id не может быть отрицательным числом!")
    private Long userId;               // кто отменил

    @NotBlank(message = "Имя пользователя не может быть пустым!")
    private String userName;           // имя пользователя

    @NotBlank(message = "Email должен быть заполненным!")
    @Email(message = "Не корректный формат email!")
    private String userEmail;          // email пользователя

    @NotBlank(message = "Название фильма не может быть пустым!")
    @Size(max = 50, message = "Название фильма не может привышать более 50 символов!")
    private String movieName;          // название фильма

    @NotNull(message = "Время и дата сеанса не может быть пустым!")
    private LocalDateTime showTime;    // время и дата сеанса

    @NotBlank(message = "Номер место не может быть пустым!")
    @Pattern(regexp = "^[A-Z]\\d{1,3}$", message = "Формат место должно быть виде A1,B2")
    private String seatNumber;         // место

    @NotNull(message = "Сумма возврата не может быть null")
    @DecimalMin(value = "0.0",inclusive = true, message = "Сумма возврата не может быть отрицательным числом")
    @Digits(integer = 10,fraction = 2, message = "Сумма возврата: до 10 целых и 2 дробных знака")
    private BigDecimal refundAmount;   // сумма возврата

    @NotBlank(message = "Id не может быть пустым!")
    private String paymentId;          // Id оригинального платежа

    @NotNull(message = "Время отмены билета не может быть пустым!")
    private LocalDateTime cancelledAt; // время отмена билета

    @Size(max = 500, message = "Описание не должно привышать более 500 символов!")
    private String cancellationReason; // причина отмена билета
}

