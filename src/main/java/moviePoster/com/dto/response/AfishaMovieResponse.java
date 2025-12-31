package moviePoster.com.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.List;

@Getter
@Setter
@Builder
public class AfishaMovieResponse {

    private String title;
    private Integer duration;
    private Set<String> genres;
    private List<SessionShortResponse> sessions;

}
