package moviePoster.com.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserRequestDto {

    private String name;
    private LocalDate dateOfBirth;
    private String phone;
    private String email;
    private String password;
}
