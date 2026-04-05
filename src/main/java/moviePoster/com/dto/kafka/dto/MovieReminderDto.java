package moviePoster.com.dto.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieReminderDto {

    private String userEmail;
    private String userName;
    private String movieName;
    private String ticketId;
    private LocalDateTime showTime;
    private String seatNumber;
    private String cinemaName;
}
