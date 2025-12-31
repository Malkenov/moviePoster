package moviePoster.com.mapper;

import moviePoster.com.dto.request.SessionRequestDto;
import moviePoster.com.dto.response.SessionResponseDto;
import moviePoster.com.entity.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cinemas", ignore = true)
    @Mapping(target = "movies", ignore = true)
    Session toEntity(SessionRequestDto dto);

    SessionResponseDto toDto(Session session);
}
