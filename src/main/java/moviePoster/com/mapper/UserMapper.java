package moviePoster.com.mapper;


import moviePoster.com.dto.request.UserRequestDto;
import moviePoster.com.dto.response.UserResponseDto;
import moviePoster.com.entity.UserSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "city", ignore = true)
    @Mapping(target = "favouritesCinemas", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "authSessions", ignore = true)
    @Mapping(target = "otpCodes", ignore = true)
    @Mapping(target = "role", ignore = true)
    UserSession toEntity(UserRequestDto dto);

    UserResponseDto toDto(UserSession user);
}
