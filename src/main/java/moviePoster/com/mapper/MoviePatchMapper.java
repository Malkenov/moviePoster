package moviePoster.com.mapper;

import moviePoster.com.dto.request.MovieRequestDto;
import moviePoster.com.domain.entity.MovieEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MoviePatchMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "isPremiere", ignore = true)
    @Mapping(target = "ageLimit", ignore = true)
    @Mapping(target = "language", ignore = true)
    @Mapping(target = "releaseDate", ignore = true)
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "sessionList", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    void updateMovieFromDto(MovieRequestDto dto, @MappingTarget MovieEntity movie);
}
