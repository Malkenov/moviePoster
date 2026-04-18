package moviePoster.com.mapper;

import moviePoster.com.dto.request.MovieRequestDto;
import moviePoster.com.dto.response.MovieResponseDto;
import moviePoster.com.domain.entity.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "sessionList", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    MovieEntity toEntity(MovieRequestDto request);

    @Mapping(target = "genres",
            expression = "java(movie.getGenres().stream().map(g -> g.getName()).collect(java.util.stream.Collectors.toSet()))")
    MovieResponseDto toDto(MovieEntity movie);
}

