package moviePoster.com.repository;

import moviePoster.com.entity.MovieEntity;
import moviePoster.com.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {

    List<SessionEntity> findByMovieName(String name);

    List<SessionEntity> findByCinemaByName(String name);

    List<SessionEntity> findCinemaByCity(String name);

    boolean existsByName(String name);

    void delete(String name);

    List<SessionEntity> findByStartTimeBetween(LocalDateTime from, LocalDateTime to);



    @Query("""
    SELECT s FROM SessionEntity s
    JOIN FETCH s.movie m
    JOIN FETCH s.cinema c
    WHERE (:city IS NULL OR c.city = :city)
      AND (:startOfDay IS NULL OR s.startTime >= :startOfDay)
      AND (:endOfDay IS NULL OR s.startTime < :endOfDay)
    ORDER BY s.startTime
""")

    List<SessionEntity> findByMovieIn(List<MovieEntity> movies);

}


