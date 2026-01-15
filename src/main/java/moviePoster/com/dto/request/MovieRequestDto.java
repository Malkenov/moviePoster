package moviePoster.com.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.List;

@Getter
@Setter
@Builder
public class MovieRequestDto {

    private String title;
    private String description;
    private Integer duration;
    private Double ageRating;
    private Set<Long> genreId;
    private List<Long> sessionId;
    private Long eventsId;
}
