package moviePoster.com.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AfishaMovieResponse {

    private String title;
    private Integer duration;
    private Set<String> genres;
    private List<SessionShortResponse> sessions;

}
