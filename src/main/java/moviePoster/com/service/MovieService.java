package moviePoster.com.service;

import lombok.AllArgsConstructor;
import moviePoster.com.config.cached.CacheService;
import moviePoster.com.dto.request.MovieRequestDto;
import moviePoster.com.dto.response.MovieResponseDto;
import moviePoster.com.domain.entity.GenreEntity;
import moviePoster.com.domain.entity.MovieEntity;
import moviePoster.com.mapper.MovieMapper;
import moviePoster.com.mapper.MoviePatchMapper;
import moviePoster.com.repository.GenreRepository;
import moviePoster.com.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final MovieMapper movieMapper;
    private final MoviePatchMapper moviePatchMapper;
    private final CacheService cacheService;

    public MovieResponseDto create(MovieRequestDto dto) {
        Set<GenreEntity> genreSet = new HashSet<>(genreRepository.findAllById(dto.getGenreId()));
        MovieEntity movie = movieMapper.toEntity(dto);
        movie.setGenres(genreSet);
        MovieEntity saved = movieRepository.save(movie);
        return movieMapper.toDto(saved);
    }

    public List<MovieResponseDto> getAll() {
        String key = "ALL_MOVIE";
        @SuppressWarnings("unchecked")
        List<MovieResponseDto> cached = (List<MovieResponseDto>) cacheService.get(key);
        if (cached != null) {
            return cached;
        }
        List<MovieResponseDto> movies = movieRepository.findAll()
                .stream()
                .map(movieMapper::toDto)
                .toList();
        cacheService.put(key, movies, Duration.ofMinutes(10));
        return movies;
    }

    public MovieResponseDto getByName(String name) {
        MovieEntity movie = movieRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Фильм не найден!"));
        return movieMapper.toDto(movie);
    }

    public Page<MovieResponseDto> getMovieByPage(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return movieRepository.findAll(pageable)
                .map(movieMapper::toDto);
    }

    public MovieResponseDto updateMovie(String name, MovieRequestDto dto) {
        MovieEntity movie = movieRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Фильм не найден!"));
        moviePatchMapper.updateMovieFromDto(dto, movie);
        movieRepository.save(movie);
        return movieMapper.toDto(movie);
    }

    public void deleteByName(String name) {
        if (!movieRepository.existsByName(name)) {
            throw new RuntimeException("Нету такого фильма в списке!");
        }
        movieRepository.deleteByName(name);
    }
}