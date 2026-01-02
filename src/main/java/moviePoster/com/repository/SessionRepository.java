package moviePoster.com.repository;

import moviePoster.com.entity.Movie;
import moviePoster.com.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByMovieName(String name);

    List<Session> findByCinemaByName(String name);

    List<Session> findCinemaByCity(String name);

    boolean existsByName(String name);

    void delete(String name);



    @Query("""
    SELECT s FROM Session s
    JOIN FETCH s.movie m
    JOIN FETCH s.cinema c
    WHERE (:city IS NULL OR c.city = :city)
      AND (:startOfDay IS NULL OR s.startTime >= :startOfDay)
      AND (:endOfDay IS NULL OR s.startTime < :endOfDay)
    ORDER BY s.startTime
""")

    List<Session> findByMovieIn(List<Movie> movies);

}


