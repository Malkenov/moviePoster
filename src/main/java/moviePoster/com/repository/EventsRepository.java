package moviePoster.com.repository;

import moviePoster.com.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Events, Long> {

    boolean existsByName(String name);

    void deleteByName(String name);
}
