package moviePoster.com.service;

import lombok.AllArgsConstructor;
import moviePoster.com.dto.request.EventsRequestDto;
import moviePoster.com.dto.response.EventsResponseDto;
import moviePoster.com.entity.Events;
import moviePoster.com.mapper.EventsMapper;
import moviePoster.com.repository.EventsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EventsService {

    private final EventsRepository eventsRepository;
    private final EventsMapper eventsMapper;

    public EventsResponseDto create(EventsRequestDto dto){
        Events events = eventsMapper.toEntity(dto);
        Events save = eventsRepository.save(events);
        return eventsMapper.toDto(save);
    }

    public List<EventsResponseDto> getAll(){
        return eventsRepository.findAll()
                .stream()
                .map(eventsMapper::toDto)
                .toList();
    }


    public void delete(String name){
        if(!eventsRepository.existsByName(name)){
            throw new RuntimeException("Нету такого события!");
        }
            eventsRepository.deleteByName(name);
    }

}
