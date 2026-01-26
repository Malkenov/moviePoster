package moviePoster.com.mapper;


import moviePoster.com.dto.request.GenreRequestDto;
import moviePoster.com.dto.response.GenreResponseDto;
import moviePoster.com.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    @Mapping(target = "movies", ignore = true)
    @Mapping(target = "performanceList", ignore = true)
    Genre toEntity(GenreRequestDto dto);

    GenreResponseDto toDto(Genre genre);
}
