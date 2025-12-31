package moviePoster.com.repository;

import moviePoster.com.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    Optional<Cinema> findByName(String name);

    boolean existsByName(String name);

    void delete(String name);
}
