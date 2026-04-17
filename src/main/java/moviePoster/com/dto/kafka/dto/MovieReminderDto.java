package moviePoster.com.dto.kafka.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieReminderDto {

    @NotBlank(message = "Email должен быть заполненным!")
    @Email(message = "Не корректный формат email!")
    private String userEmail;

    @NotBlank(message = "Имя пользователя не может быть пустым!")
    @Size(max = 25,message = "Имя пользователя не должно привышать более 25 символов!")
    private String userName;

    @NotBlank(message = "Название фильма не может быть пустым!")
    private String movieName;

    @NotNull(message = "Id билета не может быть null!")
    @Positive(message = "Id билета не может быть отрицательным числом!")
    private String ticketId;

    @NotNull(message = "Время и дата сеанса не может быть пустым!")
    private LocalDateTime showTime;

    @NotBlank(message = "Номер место не может быть пустым!")
    @Pattern(regexp = "^[A-Z]\\d{1-3}", message = "Формат место должно быть ввиде A1,B2")
    private String seatNumber;

    @NotBlank(message = "Название кинотеатра не можеь быть пустым!")
    private String cinemaName;
}
