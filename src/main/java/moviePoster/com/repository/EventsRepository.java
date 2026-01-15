package moviePoster.com.repository;

import moviePoster.com.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Long, Events> {
}
