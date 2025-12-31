package moviePoster.com.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class SessionShortResponse {

    private String cinemaName;
    private LocalDateTime startTime;
    private BigDecimal price;

}
