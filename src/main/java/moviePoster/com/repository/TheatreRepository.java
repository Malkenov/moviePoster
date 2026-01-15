package moviePoster.com.repository;

import moviePoster.com.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Long, Theatre> {
}
