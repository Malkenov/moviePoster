package moviePoster.com.service.producer;



import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import moviePoster.com.config.KafkaTopicsConfig;
import moviePoster.com.dto.kafka.dto.KafkaPurchasedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class KafkaPurchasedProducer {

    private final KafkaTemplate<String, KafkaPurchasedDto> kafkaTemplate;
    // KafkaTemplate - инструмент от Spring для отправки сообщений в Kafka

    public void send(@Valid KafkaPurchasedDto dto){
        kafkaTemplate.send(KafkaTopicsConfig.TICKET_PURCHASED, dto);
        log.info("Отправлено: {}", dto);
    }
}

