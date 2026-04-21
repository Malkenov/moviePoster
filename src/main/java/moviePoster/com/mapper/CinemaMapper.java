package moviePoster.com.mapper;

import moviePoster.com.dto.request.CinemaRequestDto;
import moviePoster.com.dto.response.CinemaResponseDto;
import moviePoster.com.domain.entity.CinemaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CinemaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "city", ignore = true)
    @Mapping(target = "sessionList", ignore = true)
    @Mapping(target = "favouritesCinemas", ignore = true)
    @Mapping(target = "hall", ignore = true)
    CinemaEntity toEntity(CinemaRequestDto dto);

    @Mapping(target = "city", expression = "java(cinema.getCity() != null ? cinema.getCity().getName() : null)")
    CinemaResponseDto toDto(CinemaEntity cinema);
}
