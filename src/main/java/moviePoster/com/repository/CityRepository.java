package moviePoster.com.repository;

import moviePoster.com.domain.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<Long, CityEntity> {
}
