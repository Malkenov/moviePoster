package moviePoster.com.repository;

import moviePoster.com.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByName(String name);

    boolean existsByName(String name);

    void deleteByName(String name);
}
