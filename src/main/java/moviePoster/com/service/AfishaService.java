package moviePoster.com.service;

import lombok.AllArgsConstructor;
import moviePoster.com.dto.response.AfishaMovieResponse;
import moviePoster.com.entity.Movie;
import moviePoster.com.entity.Session;
import moviePoster.com.mapper.AfishaMapper;
import moviePoster.com.repository.MovieRepository;
import moviePoster.com.repository.SessionRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;
import java.time.LocalDate;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class AfishaService {

    private final SessionRepository sessionRepository;
    private final AfishaMapper afishaMapper;
    private final MovieRepository movieRepository;

    public Page<AfishaMovieResponse> getAfisha(
            String city,
            LocalDate date,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("title").ascending());

        LocalDateTime startOfDay = date != null ? date.atStartOfDay() : null;
        LocalDateTime endOfDay   = date != null ? date.plusDays(1).atStartOfDay() : null;

        Page<Movie> moviePage = movieRepository.findMoviesForAfisha(
                city,
                startOfDay,
                endOfDay,
                pageable
        );

        List<Movie> movies = moviePage.getContent();
        if (movies.isEmpty()) {
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        }

        List<Session> sessions = sessionRepository.findByMovieIn(movies);

        Map<Long, List<Session>> sessionsByMovieId = sessions.stream()
                .collect(Collectors.groupingBy(s -> s.getMovies().getId()));

        List<AfishaMovieResponse> movieResponses = movies.stream()
                .map(movie -> {
                    AfishaMovieResponse response = afishaMapper.toMovie(movie);
                    List<Session> movieSessions = sessionsByMovieId.getOrDefault(movie.getId(), Collections.emptyList());
                    response.setSessions(afishaMapper.toSessionList(movieSessions));
                    return response;
                })
                .toList();

        return new PageImpl<>(movieResponses, pageable, moviePage.getTotalElements());
    }


}
