package moviePoster.com.repository;

import org.springframework.data.repository.query.Param;
import moviePoster.com.domain.entity.MovieEntity;
import moviePoster.com.domain.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SessionRepository extends JpaRepository<SessionEntity, Long> {

    List<SessionEntity> findByMovies_Name(String name);

    List<SessionEntity> findByCinemas_Name(String name);

    List<SessionEntity> findCinemas_City(String name);

    boolean existsByMovies_Name(String name);

    List<SessionEntity> findByStartTimeBetween(LocalDateTime from, LocalDateTime to);


    @Query("SELECT s FROM SessionEntity s JOIN FETCH s.cinemas WHERE s.movies IN :movies")
    List<SessionEntity> findByMovieIn(@Param("movies") List<MovieEntity> movies);

}


