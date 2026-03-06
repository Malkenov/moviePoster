package moviePoster.com.mapper;

import moviePoster.com.dto.request.MovieRequestDto;
import moviePoster.com.entity.MovieEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MoviePatchMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMovieFromDto(MovieRequestDto dto, @MappingTarget MovieEntity movie);
}
