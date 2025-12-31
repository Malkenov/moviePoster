package moviePoster.com.repository;

import moviePoster.com.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByMovieName(String name);

    List<Session> findByCinemaByName(String name);

    List<Session> findCinemaByCity(String name);


    boolean existsByName(String name);

    void delete(String name);
}
