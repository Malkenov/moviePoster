package moviePoster.com.mapper;

import moviePoster.com.dto.request.CinemaRequestDto;
import moviePoster.com.dto.response.CinemaResponseDto;
import moviePoster.com.domain.entity.CinemaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CinemaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sessionList", ignore = true)
    CinemaEntity toEntity(CinemaRequestDto dto);

    CinemaResponseDto toDto(CinemaEntity cinema);
}
