package moviePoster.com.service;

import lombok.AllArgsConstructor;
import moviePoster.com.dto.request.SessionRequestDto;
import moviePoster.com.dto.response.SessionResponseDto;
import moviePoster.com.entity.CinemaEntity;
import moviePoster.com.entity.MovieEntity;
import moviePoster.com.entity.SessionEntity;
import moviePoster.com.mapper.SessionMapper;
import moviePoster.com.repository.CinemaRepository;
import moviePoster.com.repository.MovieRepository;
import moviePoster.com.repository.SessionRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@AllArgsConstructor
@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final MovieRepository movieRepository;
    private final CinemaRepository cinemaRepository;
    private final SessionMapper sessionMapper;


    public SessionResponseDto create(SessionRequestDto dto){

        MovieEntity movie = movieRepository.findByName(dto.getMovie())
                .orElseThrow(() -> new RuntimeException("Фильм не найден!"));

        CinemaEntity cinema = cinemaRepository.findByName(dto.getCinema())
                .orElseThrow(() -> new RuntimeException("Кинотеатр не найден!"));

        SessionEntity session = sessionMapper.toEntity(dto);
        session.setMovies(movie);
        session.setCinemas(cinema);

        SessionEntity save = sessionRepository.save(session);
        return sessionMapper.toDto(save);
    }

    public List<SessionResponseDto> getAll(){
        return sessionRepository.findAll()
                .stream()
                .map(sessionMapper::toDto)
                .toList();
    }

    public List<SessionResponseDto> getByMovieName(String name){
        return sessionRepository.findByMovies_Name(name)
                .stream()
                .map(sessionMapper::toDto)
                .toList();
    }

    public List<SessionResponseDto> getCinemaByName(String name){
        return sessionRepository.findByCinemasBy_Name(name)
                .stream()
                .map(sessionMapper::toDto)
                .toList();
    }

    public Page<SessionResponseDto> getSessionByPage(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return sessionRepository.findAll(pageable)
                .map(sessionMapper::toDto);
    }

    public void delete(String name) {
        if (!sessionRepository.existsByMovies_Name(name)) { // ← было existsByName
            throw new RuntimeException("Сессия не найдена");
        }
        SessionEntity session = sessionRepository.findByMovies_Name(name)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Сессия не найдена"));
        sessionRepository.delete(session);
    }
}
