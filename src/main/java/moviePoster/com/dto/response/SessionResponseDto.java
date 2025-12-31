package moviePoster.com.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class SessionResponseDto {

    private String movie;
    private String cinema;
    private Integer startTime;
    private BigDecimal price;
}
