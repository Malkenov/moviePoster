package moviePoster.com.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ConcertRequestDto {

    private String name;
    private String title;
    private int age_limit;
    private Long eventsId;
    private Long venueId;
}
