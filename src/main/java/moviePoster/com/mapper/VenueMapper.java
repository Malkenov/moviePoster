package moviePoster.com.mapper;

import moviePoster.com.dto.request.VenueRequestDto;
import moviePoster.com.dto.response.VenueResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VenueMapper {

    @Mapping(target = "concertList",ignore = true)
    @Mapping(target = "familyList",ignore = true)
    Venue toEntity(VenueRequestDto dto);


    VenueResponseDto toDto(Venue venue);
}
