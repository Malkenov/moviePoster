package moviePoster.com.repository;

import moviePoster.com.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Long, CategoryEntity> {
}
