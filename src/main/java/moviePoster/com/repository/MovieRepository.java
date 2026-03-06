package moviePoster.com.repository;

import moviePoster.com.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    Optional<MovieEntity> findByName(String name);

    boolean existsByName(String name);

    void deleteByName(String name);

    @Query("""
    SELECT DISTINCT m FROM MovieEntity m
    JOIN m.sessions s
    JOIN s.cinema c
    LEFT JOIN FETCH m.genres g
    WHERE (:city IS NULL OR c.city = :city)
      AND (:startOfDay IS NULL OR s.startTime >= :startOfDay)
      AND (:endOfDay IS NULL OR s.startTime < :endOfDay)
""")
    Page<MovieEntity> findMoviesForAfisha(
            @Param("city") String city,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay,
            Pageable pageable
    );



}
