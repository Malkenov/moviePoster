package moviePoster.com.repository;

import moviePoster.com.entity.TicketEntity;
import moviePoster.com.enums.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SeatRepository extends JpaRepository<TicketEntity, Long> {

    // ищет все истекшие брони
    List<TicketEntity> findByStatusAndReservedUntilBefore(SeatStatus status, LocalDateTime now);
}
