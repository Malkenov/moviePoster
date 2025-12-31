package moviePoster.com.dto.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class SessionRequestDto{

    private String movie;
    private String cinema;
    private Integer startTime;
    private BigDecimal price;
}
