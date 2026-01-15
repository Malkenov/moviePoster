package moviePoster.com.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class VenueResponseDto {

    private String name;
    private String title;
    private String address;
    private String city;
    private List<Long> concertId;
    private List<Long> familyId;
}
