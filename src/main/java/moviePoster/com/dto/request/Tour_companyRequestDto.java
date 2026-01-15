package moviePoster.com.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
public class Tour_companyRequestDto {

    private String name;
    private String title;
    private String city;
    private LocalTime localTime;
    private List<Long> tourId;

}
