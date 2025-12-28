package moviePoster.com.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class MovieResponseDto {

    private String title;
    private String description;
    private Integer duration;
    private Double ageRating;
    private Set<String> genres;
}
