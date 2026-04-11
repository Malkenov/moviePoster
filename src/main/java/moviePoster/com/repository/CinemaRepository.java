package moviePoster.com.repository;

import moviePoster.com.domain.entity.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CinemaRepository extends JpaRepository<CinemaEntity, Long> {

    Optional<CinemaEntity> findByName(String name);

    boolean existsByName(String name);

    void delete(String name);
}
