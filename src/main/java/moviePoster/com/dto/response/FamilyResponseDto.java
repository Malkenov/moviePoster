package moviePoster.com.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FamilyResponseDto {

    private String name;
    private String title;
    private int age_limit;
    private Double price;
}
