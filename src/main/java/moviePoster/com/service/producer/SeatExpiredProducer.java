package moviePoster.com.service.producer;

import moviePoster.com.config.KafkaTopicsConfig;
import moviePoster.com.dto.kafka.dto.SeatReservationExpiredDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatExpiredProducer {

    private final KafkaTemplate<String, SeatReservationExpiredDto> kafkaTemplate;

    public void send(SeatReservationExpiredDto dto){
        kafkaTemplate.send(KafkaTopicsConfig.SEAT_RESERVATION_EXPIRED,dto);
        System.out.println("Бронь истекла: " + dto);
    }
}

