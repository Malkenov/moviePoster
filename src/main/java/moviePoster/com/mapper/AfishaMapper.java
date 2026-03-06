package moviePoster.com.mapper;

import moviePoster.com.dto.response.AfishaMovieResponse;
import moviePoster.com.dto.response.SessionShortResponse;
import moviePoster.com.entity.MovieEntity;
import moviePoster.com.entity.SessionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AfishaMapper {

    @Mapping(target = "sessions", ignore = true)
    AfishaMovieResponse toMovie(MovieEntity movie);

    @Mapping(target = "cinemaName", source = "cinemas.name")
    SessionShortResponse toSession(SessionEntity session);

    List<SessionShortResponse> toSessionList(List<SessionEntity> sessions);

}
