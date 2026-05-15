package moviePoster.com.dto.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiRecommendationResponse {
    private String basedOn;
    private String recommendations;
}