package moviePoster.com.service;

import lombok.AllArgsConstructor;

import moviePoster.com.Cached.CacheService;
import moviePoster.com.document.MovieDocument;
import moviePoster.com.dto.request.MovieRequestDto;
import moviePoster.com.dto.response.MovieResponseDto;
import moviePoster.com.entity.GenreEntity;
import moviePoster.com.entity.MovieEntity;
import moviePoster.com.mapper.MovieMapper;
import moviePoster.com.mapper.MoviePatchMapper;
import moviePoster.com.repository.GenreRepository;
import moviePoster.com.repository.MovieRepository;
import moviePoster.com.repository.MovieSearchRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Duration;
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
    private final CacheService cacheService;
    private final MovieSearchRepository movieSearchRepository;
    private final MovieDocument document;

    private final RestTemplateBuilder restTemplateBuilder;

    public MovieResponseDto create(MovieRequestDto dto){

        Set<GenreEntity> genreSet = new HashSet<>(genreRepository.findAllById(dto.getGenreId()));
        MovieEntity movie = movieMapper.toEntity(dto);
        movie.setGenres(genreSet);

        MovieEntity saved = movieRepository.save(movie);

        MovieDocument document = MovieDocument.builder()
                .id(saved.getId())
                .name(saved.getName())
                .title(saved.getTitle())
                .build();

        movieSearchRepository.save(document);


        return movieMapper.toDto(saved);
    }

    public Page<MovieDocument> searchMovies(String text, int page, int size){

        Pageable pageable = PageRequest.of(page, size);

        return movieSearchRepository.searchByQuery(text, pageable);
    }

    public List<MovieResponseDto> getAll(){

        String key = "ALL_MOVIE";

        List<MovieResponseDto> cached = (List<MovieResponseDto>) cacheService.get(key);
        if(cached != null){
            return cached;
        }

        List<MovieResponseDto> movies = movieRepository.findAll()
                .stream()
                .map(movieMapper::toDto)
                .toList();

        cacheService.put(key,movies, Duration.ofMinutes(10));

        return movies;
    }

    public MovieResponseDto getByName(String name){
        MovieEntity movie = movieRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Фильм не найден!"));
        return movieMapper.toDto(movie);
    }

    public Page<MovieResponseDto> getMovieByPage(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return movieRepository.findAll(pageable)
                .map(movieMapper::toDto);
    }

    public MovieResponseDto updateMovie(String name,MovieRequestDto dto){
        MovieEntity movie = movieRepository.findByName(name)
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
