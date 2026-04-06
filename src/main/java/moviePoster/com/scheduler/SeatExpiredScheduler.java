package moviePoster.com.scheduler;

import lombok.RequiredArgsConstructor;
import moviePoster.com.dto.kafka.dto.SeatReservationExpiredDto;
import moviePoster.com.dto.request.TicketRequestDto;
import moviePoster.com.entity.TicketEntity;
import moviePoster.com.enums.SeatStatus;
import moviePoster.com.repository.SeatRepository;
import moviePoster.com.service.producer.SeatExpiredProducer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatExpiredScheduler {

    private final SeatRepository seatRepository;
    private final SeatExpiredProducer expiredProducer;

    @Scheduled(fixedRate = 60000)
    public void checkExpiredReserved(){
        List<TicketEntity> expiredTicked = seatRepository.findByStatusAndReservedUntilBefore(SeatStatus.RESERVED, LocalDateTime.now());

        for(TicketEntity ticketEntity: expiredTicked){
            SeatReservationExpiredDto dto = new SeatReservationExpiredDto();
            dto.setSeatId(ticketEntity.getId());
            dto.setSessionId(ticketEntity.getSession().getId());
            dto.setUserId(ticketEntity.getUser().getId());
            dto.setExpiredAt(LocalDateTime.now());

            expiredProducer.send(dto);
        }
    }
}
