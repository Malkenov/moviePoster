package moviePoster.com.mapper;

import moviePoster.com.dto.request.GenreRequestDto;
import moviePoster.com.dto.response.GenreResponseDto;
import moviePoster.com.domain.entity.GenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "movies", ignore = true)
    GenreEntity toEntity(GenreRequestDto dto);

    GenreResponseDto toDto(GenreEntity genre);
}
