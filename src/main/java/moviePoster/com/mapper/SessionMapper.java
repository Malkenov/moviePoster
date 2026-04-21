package moviePoster.com.mapper;

import moviePoster.com.dto.request.SessionRequestDto;
import moviePoster.com.dto.response.SessionResponseDto;
import moviePoster.com.domain.entity.SessionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "cinemas", ignore = true)
    @Mapping(target = "movies", ignore = true)
    @Mapping(target = "ticket", ignore = true)
    @Mapping(target = "seat", ignore = true)
    SessionEntity toEntity(SessionRequestDto dto);

    @Mapping(target = "movie",  expression = "java(session.getMovies()  != null ? session.getMovies().getName()  : null)")
    @Mapping(target = "cinema", expression = "java(session.getCinemas() != null ? session.getCinemas().getName() : null)")
    SessionResponseDto toDto(SessionEntity session);
}
