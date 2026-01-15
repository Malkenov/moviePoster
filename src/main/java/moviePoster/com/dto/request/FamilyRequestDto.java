package moviePoster.com.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FamilyRequestDto {

    private String name;
    private String title;
    private int age_limit;
    private Double price;
}
