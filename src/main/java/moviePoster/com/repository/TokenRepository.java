package moviePoster.com.repository;

import moviePoster.com.entity.Token.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    @Query(value = "SELECT t FROM TokenEntity t " +
            "INNER JOIN UserEntity u on t.userEntity.id = u.id " +
            "WHERE u.id = :userId AND (t.expired = false or t.revoked = false )")
    List<TokenEntity> findAllValidTokenByUser(Long userId);

    Optional<TokenEntity> findByToken(String token);
}
