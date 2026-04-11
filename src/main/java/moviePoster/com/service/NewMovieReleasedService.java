package moviePoster.com.service;

import lombok.RequiredArgsConstructor;
import moviePoster.com.domain.entity.MovieEntity;
import moviePoster.com.domain.entity.UserSessionEntity;
import moviePoster.com.dto.kafka.dto.NewMovieReleasedDto;
import moviePoster.com.repository.MovieRepository;
import moviePoster.com.repository.UserRepository;
import moviePoster.com.service.producer.NewMovieReleasedProducer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewMovieReleasedService {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final NewMovieReleasedProducer newMovieReleasedProducer;

    public void notifyNewMovie(Long movieId){
        MovieEntity movie = movieRepository.findById(movieId)
                .orElseThrow(()-> new RuntimeException("Фильм не найден!"));

        // тут берем название кинотератров из сеансов
        List<String> cinemas = movie.getSessionList()
                .stream()
                .map(session -> session.getCinemas().getName())
                .toList();

        List<UserSessionEntity> user = userRepository.findAll();

        for(UserSessionEntity users: user){
        NewMovieReleasedDto dto = new NewMovieReleasedDto();
        dto.setUserEmail(users.getEmail());
        dto.setUserName(users.getName());
        dto.setMovieName(movie.getName());
        dto.setGenre(movie.getGenres().toString());
        dto.setReleaseDate(movie.getReleaseDate());
        dto.setCinemaName(cinemas);

        newMovieReleasedProducer.send(dto);
        }
    }
}
