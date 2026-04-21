package moviePoster.com.mapper;

import moviePoster.com.dto.request.UserRequestDto;
import moviePoster.com.dto.response.UserResponseDto;
import moviePoster.com.domain.entity.UserSessionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "city", ignore = true)
    @Mapping(target = "favouritesCinemas", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "authSessions", ignore = true)
    @Mapping(target = "otpCode", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "tokenEntities", ignore = true)
    @Mapping(target = "seatEntity", ignore = true)
    @Mapping(target = "phoneVerified", ignore = true)
    @Mapping(target = "emailVerified", ignore = true)
    @Mapping(target = "active", ignore = true)
    UserSessionEntity toEntity(UserRequestDto dto);

    UserResponseDto toDto(UserSessionEntity user);
}
