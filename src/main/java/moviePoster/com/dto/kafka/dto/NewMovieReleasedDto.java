package moviePoster.com.dto.kafka.dto;

import java.time.LocalDateTime;

public class NewMovieReleasedDto {

    private String userEmail;
    private String userName;
    private String movieName;
    private String genre;
    private LocalDateTime releaseDate;
    private String cinemaName;
}
