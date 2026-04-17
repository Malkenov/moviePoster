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
public class KafkaPurchasedDto {

    @NotNull(message = "Id билета не может быть null!")
    @Positive(message = "Id билета не может быть отрицательным числом!")
    private Long ticketId;             // id билета

    @NotBlank(message = "Email не может быть пустым!")
    @Email(message = "Не корректный формат email!")
    private String userEmail;          // email пользователя

    @NotBlank(message = "Имя пользователя не может быть пустым!")
    @Size(max = 25,message = "Имя пользователя не должно привышать более 25 символов!")
    private String userName;           // имя пользователя

    @NotBlank(message = "Название фильма не может быть пустым!")
    private String movieName;          // название фильма

    @NotNull(message = "Время и дата сеанса не может быть пустым!")
    private LocalDateTime showTime;    // время сеанса

    @NotBlank(message = "Название кинотеатра не можеь быть пустым!")
    private String cinemaName;         // кинотеатр

    @NotBlank(message = "Номер место не может быть пустым!")
    @Pattern(regexp = "^[A-Z]\\d{1-3}", message = "Формат место должно быть ввиде A1,B2")
    private String seatNumber;         // место
    private BigDecimal price;          // цена

    @NotNull(message = "Время покупки билета должно быть указанно!")
    private LocalDateTime purchasedAt; // когда куплен
}

