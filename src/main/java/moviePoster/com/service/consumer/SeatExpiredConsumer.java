package moviePoster.com.service.consumer;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moviePoster.com.dto.kafka.dto.SeatReservationExpiredDto;
import moviePoster.com.domain.entity.SeatEntity;
import moviePoster.com.dto.enums.SeatStatus;
import moviePoster.com.repository.SeatRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SeatExpiredConsumer {

    private final SeatRepository seatRepository;

    @Transactional
    @KafkaListener(topics = "seat-reservation-expired",groupId = "seat-notifications")
    public void listen(SeatReservationExpiredDto dto){
        log.info("Освобождаем место: {}", dto );

        SeatEntity seat = seatRepository.findById(dto.getSeatId()).orElseThrow();

        if (seat.getStatus() != SeatStatus.EXPIRING) {
            log.warn("Место {} уже обработано, статус: {}", seat.getId(), seat.getStatus());
            return;
        }

        seat.setStatus(SeatStatus.AVAILABLE);
        seat.setReservedUntil(null);
        seat.setUser(null);
        seatRepository.save(seat);

        log.info("Место {} свободно", dto.getSeatId());

    }
}
