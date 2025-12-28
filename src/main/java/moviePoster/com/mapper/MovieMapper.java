package moviePoster.com.mapper;

import moviePoster.com.dto.request.MovieRequestDto;
import moviePoster.com.dto.response.MovieResponseDto;
import moviePoster.com.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "genres", ignore = true)
    Movie toEntity(MovieRequestDto request);

    @Mapping(target = "genres", expression =
            "java(movie.getGenres().stream().map(Genre::getName).collect(java.util.stream.Collectors.toSet()))"
    )
    MovieResponseDto toDto(Movie movie);
}

