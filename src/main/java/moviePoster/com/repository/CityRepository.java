package moviePoster.com.repository;

import moviePoster.com.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<Long, City> {
}
