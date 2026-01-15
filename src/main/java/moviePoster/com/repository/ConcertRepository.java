package moviePoster.com.repository;

import moviePoster.com.entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository<Long, Concert> {
}
