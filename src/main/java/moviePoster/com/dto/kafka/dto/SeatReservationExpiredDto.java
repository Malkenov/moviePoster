package moviePoster.com.dto.kafka.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatReservationExpiredDto {

    @NotNull(message = "Id место не может быть null!")
    @Positive(message = "Id не может быть отрицательным!")
    private Long seatId;         // какое место освободить

    @NotNull(message = "Id сеанса не может быть null!")
    @Positive(message = "Id не может быть отрицательным!")
    private Long sessionId;        // какой сеанс

    @NotNull(message = "Id пользователя не может быть null!")
    @Positive(message = "Id не может быть отрицательным!")
    private Long userId;         // кто бронировал

    @NotNull(message = "Время истечения брони не может быть null!")
    @PastOrPresent(message = "Время истечения должно быть в прошлом или настоящем!")
    private LocalDateTime expiredAt;
}
