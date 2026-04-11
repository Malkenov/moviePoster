package moviePoster.com.repository;

import moviePoster.com.domain.entity.OtpCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpCodeEntity, Long> {
    Optional<OtpCodeEntity> findTopByEmailAndPurposeOrderByCreatedAtDesc(String email, String purpose);
    Optional<OtpCodeEntity> findTopByPhoneAndPurposeOrderByCreatedAtDesc(String phone, String purpose);
}
