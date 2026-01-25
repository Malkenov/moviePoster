package moviePoster.com.repository;

import moviePoster.com.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserSession,Long> {

    Optional<UserSession> findByEmail(String email);
}
