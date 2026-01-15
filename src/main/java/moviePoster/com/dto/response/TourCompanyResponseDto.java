package moviePoster.com.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
public class TourCompanyResponseDto {

    private String name;
    private String title;
    private String city;
    private LocalTime localTime;
    private List<Long> tourId;
}
