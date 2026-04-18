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

    @Mapping(target = "sessions", ignore = true)
    @Mapping(target = "title", source = "name")   // ← MovieEntity.name → AfishaMovieResponse.title
    @Mapping(target = "genres",
            expression = "java(movie.getGenres().stream().map(g -> g.getName()).collect(java.util.stream.Collectors.toSet()))")
    AfishaMovieResponse toMovie(MovieEntity movie);

    @Mapping(target = "cinemaName", source = "cinemas.name")   // работает после фикса pom.xml
    SessionShortResponse toSession(SessionEntity session);

    List<SessionShortResponse> toSessionList(List<SessionEntity> sessions);
}