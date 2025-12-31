package moviePoster.com.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CinemaRequestDto {

    private String name;
    private String city;
    private String address;
}
