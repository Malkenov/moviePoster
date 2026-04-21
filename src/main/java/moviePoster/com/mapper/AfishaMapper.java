package moviePoster.com.mapper;

import moviePoster.com.dto.response.AfishaMovieResponse;
import moviePoster.com.dto.response.SessionShortResponse;
import moviePoster.com.domain.entity.MovieEntity;
import moviePoster.com.domain.entity.SessionEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AfishaMapper {

    @Mapping(target = "title", source = "name")
    @Mapping(target = "sessions", ignore = true)
    @Mapping(target = "genres",
            expression = "java(movie.getGenres().stream().map(g -> g.getName()).collect(java.util.stream.Collectors.toSet()))")
    AfishaMovieResponse toMovie(MovieEntity movie);

    @Mapping(target = "cinemaName",
            expression = "java(session.getCinemas() != null ? session.getCinemas().getName() : null)")
    @Mapping(target = "price", ignore = true)
    SessionShortResponse toSession(SessionEntity session);

    List<SessionShortResponse> toSessionList(List<SessionEntity> sessions);
}
