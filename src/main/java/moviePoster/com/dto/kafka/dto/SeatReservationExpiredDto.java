package moviePoster.com.dto.kafka.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatReservationExpiredDto {

    private Long seatId;         // какое место освободить
    private Long sessionId;        // какой сеанс
    private Long userId;         // кто бронировал
    private LocalDateTime expiredAt; // когда истекло
}
