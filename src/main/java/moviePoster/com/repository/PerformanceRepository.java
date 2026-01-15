package moviePoster.com.repository;

import moviePoster.com.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepository extends JpaRepository<Long, Performance> {
}
