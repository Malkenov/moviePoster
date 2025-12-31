package moviePoster.com.service;

import lombok.AllArgsConstructor;
import moviePoster.com.dto.response.AfishaMovieResponse;
import moviePoster.com.entity.Movie;
import moviePoster.com.entity.Session;
import moviePoster.com.mapper.AfishaMapper;
import moviePoster.com.repository.SessionRepository;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.time.LocalDate;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class AfishaService {

    private final SessionRepository sessionRepository;
    private final AfishaMapper afishaMapper;

    public List<AfishaMovieResponse> getAfisha(String city, LocalDate date) {

        List<Session> sessions = sessionRepository.findForAfisha(city, date);

        Map<Movie, List<Session>> grouped =
                sessions.stream().collect(Collectors.groupingBy(Session::getMovies));

        return grouped.entrySet().stream()
                .map(entry -> {
                    Movie movie = entry.getKey();
                    List<Session> movieSessions = entry.getValue();

                    AfishaMovieResponse response =
                            afishaMapper.toMovie(movie);


                    response.setSessions(
                            afishaMapper.toSessionList(movieSessions)
                    );

                    return response;
                })
                .toList();
    }

}
