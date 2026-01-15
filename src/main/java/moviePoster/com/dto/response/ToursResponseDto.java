package moviePoster.com.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ToursResponseDto {

    private String name;
    private String title;
    private int age_limit;
    private Double price;
    private LocalDateTime dateTime;
    private Long eventsId;
    private Long tourCompany;
}
