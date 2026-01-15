package moviePoster.com.repository;

import moviePoster.com.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Long, Family> {
}
