package moviePoster.com.dto.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewMovieReleasedDto {

    private String userEmail;
    private String userName;
    private String movieName;
    private String genre;
    private LocalDate releaseDate;
    private List<String> cinemaName;
}
