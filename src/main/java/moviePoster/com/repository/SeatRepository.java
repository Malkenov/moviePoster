package moviePoster.com.repository;

import moviePoster.com.entity.SeatEntity;
import moviePoster.com.entity.TicketEntity;
import moviePoster.com.enums.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SeatRepository extends JpaRepository<SeatEntity, Long> {

    // ищет все истекшие брони
    List<SeatEntity> findByStatusAndReservedUntilBefore(SeatStatus status, LocalDateTime now);
}
