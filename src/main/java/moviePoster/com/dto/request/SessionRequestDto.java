package moviePoster.com.dto.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class SessionRequestDto{

    private String movie;
    private String cinema;
    private LocalDateTime startTime;
    private BigDecimal price;
}
