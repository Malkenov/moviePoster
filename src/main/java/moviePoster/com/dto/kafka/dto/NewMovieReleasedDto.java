package moviePoster.com.dto.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewMovieReleasedDto {

    private String userEmail;
    private String userName;
    private String movieName;
    private String genre;
    private LocalDateTime releaseDate;
    private String cinemaName;
}
