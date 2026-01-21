package moviePoster.com.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TicketRequestDto {

    private int row;
    private int place;
}
