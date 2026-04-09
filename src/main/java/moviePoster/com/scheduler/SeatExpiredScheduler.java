package moviePoster.com.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moviePoster.com.dto.kafka.dto.SeatReservationExpiredDto;
import moviePoster.com.entity.SeatEntity;
import moviePoster.com.enums.SeatStatus;
import moviePoster.com.repository.SeatRepository;
import moviePoster.com.service.producer.SeatExpiredProducer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SeatExpiredScheduler {

    private final SeatRepository seatRepository;
    private final SeatExpiredProducer expiredProducer;

    @Scheduled(fixedRate = 60000)
    public void checkExpiredReserved(){
        List<SeatEntity> expiredTicked = seatRepository.findByStatusAndReservedUntilBefore(SeatStatus.RESERVED, LocalDateTime.now());

        for(SeatEntity seat: expiredTicked){
            SeatReservationExpiredDto dto = new SeatReservationExpiredDto();
            dto.setSeatId(seat.getId());
            dto.setSessionId(seat.getSessionEntity().getId());
            dto.setUserId(seat.getUser().getId());
            dto.setExpiredAt(LocalDateTime.now());

            expiredProducer.send(dto);

            log.info("Найдена истекшая бронь: {}", seat.getId());
        }
    }
}
