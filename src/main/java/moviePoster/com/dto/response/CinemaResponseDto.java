package moviePoster.com.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CinemaResponseDto {

    private String name;
    private String city;
    private String address;
}
