package moviePoster.com.service;

import lombok.AllArgsConstructor;

import moviePoster.com.dto.request.MovieRequestDto;
import moviePoster.com.dto.response.MovieResponseDto;
import moviePoster.com.entity.Genre;
import moviePoster.com.entity.Movie;
import moviePoster.com.mapper.MovieMapper;
import moviePoster.com.mapper.MoviePatchMapper;
import moviePoster.com.repository.GenreRepository;
import moviePoster.com.repository.MovieRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

@AllArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final MovieMapper movieMapper;
    private final MoviePatchMapper moviePatchMapper;

    private final RestTemplateBuilder restTemplateBuilder;

    public MovieResponseDto create(MovieRequestDto dto){

        Set<Genre> genreSet = new HashSet<>(genreRepository.findAllById(dto.getGenreId()));
        Movie movie = movieMapper.toEntity(dto);
        movie.setGenres(genreSet);

        Movie saved = movieRepository.save(movie);
        return movieMapper.toDto(saved);
    }

    public List<MovieResponseDto> getAll(){
        return movieRepository.findAll()
                .stream()
                .map(movieMapper::toDto)
                .toList();
    }

    public MovieResponseDto getByName(String name){
        Movie movie = movieRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Фильм не найден!"));
        return movieMapper.toDto(movie);
    }

    public Page<MovieResponseDto> getMovieByPage(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return movieRepository.findAll(pageable)
                .map(movieMapper::toDto);
    }

    public MovieResponseDto updateMovie(String name,MovieRequestDto dto){
        Movie movie = movieRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Фильм не найден!"));
        movieMapper.toEntity(dto);
        moviePatchMapper.updateMovieFromDto(dto,movie);
        movieRepository.save(movie);
        return movieMapper.toDto(movie);
    }

    public void deleteByName(String name){
        if(!movieRepository.existsByName(name)){
            throw new RuntimeException("Нету такого фильма в списке!");
        }
        movieRepository.deleteByName(name);
    }
}
