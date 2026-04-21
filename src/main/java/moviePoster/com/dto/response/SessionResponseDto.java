package moviePoster.com.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class SessionResponseDto {

    private String movie;
    private String cinema;
    private LocalDateTime startTime;
}
