package moviePoster.com.repository;

import moviePoster.com.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity,Long> {
}
