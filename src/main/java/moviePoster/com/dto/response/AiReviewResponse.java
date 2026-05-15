package moviePoster.com.dto.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiReviewResponse {
    private String movieTitle;
    private String genre;
    private String plot;
    private String verdict;
    private String rawResponse;
}
