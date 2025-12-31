package moviePoster.com.mapper;

import moviePoster.com.dto.response.AfishaMovieResponse;
import moviePoster.com.dto.response.SessionShortResponse;
import moviePoster.com.entity.Movie;
import moviePoster.com.entity.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AfishaMapper {

    @Mapping(target = "sessions", ignore = true)
    AfishaMovieResponse toMovie(Movie movie);

    @Mapping(target = "cinemaName", source = "cinemas.name")
    SessionShortResponse toSession(Session session);

    List<SessionShortResponse> toSessionList(List<Session> sessions);

}
