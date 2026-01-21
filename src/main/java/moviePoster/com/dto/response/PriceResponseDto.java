package moviePoster.com.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PriceResponseDto {

    private Double amount;
    private String category;
}
