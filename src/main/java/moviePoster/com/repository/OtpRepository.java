package moviePoster.com.repository;

import moviePoster.com.entity.OtpCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpCode, Long> {
    Optional<OtpCode> findTopByEmailAndPurposeOrderByCreatedAtDesc(String email, String purpose);
    Optional<OtpCode> findTopByPhoneAndPurposeOrderByCreatedAtDesc(String phone, String purpose);
}
