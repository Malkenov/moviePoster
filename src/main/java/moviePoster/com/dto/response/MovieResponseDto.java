package moviePoster.com.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
public class MovieResponseDto {

    private String title;
    private String description;
    private Integer duration;
    private Double ageRating;
    private Set<Long> genreId;
    private List<Long> sessionId;
    private Long eventsId;
}
