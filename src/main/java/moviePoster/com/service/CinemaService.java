package moviePoster.com.service;

import lombok.AllArgsConstructor;
import moviePoster.com.dto.request.CinemaRequestDto;
import moviePoster.com.dto.response.CinemaResponseDto;
import moviePoster.com.entity.Cinema;
import moviePoster.com.mapper.CinemaMapper;
import moviePoster.com.repository.CinemaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CinemaService {

    private final CinemaRepository cinemaRepository;
    private final CinemaMapper cinemaMapper;

    public CinemaResponseDto create(CinemaRequestDto dto){
        Cinema cinema = cinemaMapper.toEntity(dto);
        Cinema saved = cinemaRepository.save(cinema);
        return cinemaMapper.toDto(saved);
    }

    public List<CinemaResponseDto> getAll(){
        return cinemaRepository.findAll()
                .stream()
                .map(cinemaMapper::toDto)
                .toList();
    }

    public CinemaResponseDto getByName(String name){
        Cinema cinema = cinemaRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Не удалось найти кинотеатр!"));
        return cinemaMapper.toDto(cinema);
    }

    public void deleteByName(String name){
        if(!cinemaRepository.existsByName(name)){
            throw new RuntimeException("Не удалось найти кинотеатр!");
        }
        cinemaRepository.delete(name);
    }
}
