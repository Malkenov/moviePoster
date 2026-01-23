package moviePoster.com.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserResponseDto {

    private String name;
    private LocalDate dateOfBirth;
    private String phone;
    private String email;
    private LocalDateTime created_at;
}
