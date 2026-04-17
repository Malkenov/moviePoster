package moviePoster.com.service.producer;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import moviePoster.com.config.KafkaTopicsConfig;
import moviePoster.com.dto.kafka.dto.SeatReservationExpiredDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class SeatExpiredProducer {

    private final KafkaTemplate<String, SeatReservationExpiredDto> kafkaTemplate;

    public void send(@Valid SeatReservationExpiredDto dto){
        kafkaTemplate.send(KafkaTopicsConfig.SEAT_RESERVATION_EXPIRED,dto);
        log.info("Бронь истекла: {}", dto);
    }
}

