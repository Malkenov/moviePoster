package moviePoster.com.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class MovieRequestDto {

    private String title;
    private String description;
    private Integer duration;
    private Double ageRating;
    private Set<Long> genreId;
}
