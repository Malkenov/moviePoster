package moviePoster.com.service;

import lombok.AllArgsConstructor;
import moviePoster.com.dto.request.GenreRequestDto;
import moviePoster.com.dto.response.GenreResponseDto;
import moviePoster.com.entity.Genre;
import moviePoster.com.mapper.GenreMapper;
import moviePoster.com.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreResponseDto create(GenreRequestDto dto){
        Genre genre = genreMapper.toEntity(dto);
        Genre save = genreRepository.save(genre);
        return genreMapper.toDto(save);
    }

    public Set<GenreResponseDto> getAll(){
        return genreRepository.findAll()
                .stream()
                .map(genreMapper::toDto)
                .collect(Collectors.toSet());
    }
}
