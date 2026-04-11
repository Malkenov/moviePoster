package moviePoster.com.service.producer;

import lombok.RequiredArgsConstructor;
import moviePoster.com.config.KafkaTopicsConfig;
import moviePoster.com.dto.kafka.dto.KafkaCancelledDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaCancelledProducer {

    private final KafkaTemplate<String, KafkaCancelledDto> kafkaTemplate;

    public void send(KafkaCancelledDto dto){
        kafkaTemplate.send(KafkaTopicsConfig.TICKET_CANCELLED,dto);
        System.out.println("Сообщение отправлено: " + dto);
    }
}
