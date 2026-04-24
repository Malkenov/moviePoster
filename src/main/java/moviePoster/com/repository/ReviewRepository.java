package moviePoster.com.repository;

import moviePoster.com.domain.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    List<ReviewEntity> findByMovies_Name(String movieName);
    List<ReviewEntity> findByUser_Email(String email);
}