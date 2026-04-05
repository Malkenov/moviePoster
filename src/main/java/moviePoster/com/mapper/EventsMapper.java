package moviePoster.com.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventsMapper {

    @Mapping(target = "concertList",ignore = true)
    @Mapping(target = "performanceList", ignore = true)
    Events toEntity(EventsRequestDto dto);

    EventsResponseDto toDto(Events events);
}
