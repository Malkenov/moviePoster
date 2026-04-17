package moviePoster.com.service.producer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moviePoster.com.config.KafkaTopicsConfig;
import moviePoster.com.dto.kafka.dto.KafkaCancelledDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class KafkaCancelledProducer {

    private final KafkaTemplate<String, KafkaCancelledDto> kafkaTemplate;

    // Если тут возникает ошибка, то вызывается метод sendToDlq()
    public void send(@Valid KafkaCancelledDto dto) {
        kafkaTemplate.send(KafkaTopicsConfig.TICKET_CANCELLED, dto)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("Сообщение отправлено: {}", dto);
                        sendToDlq(dto);
                    }else{
                        log.error("Ошибка в отправке");
                    }
                });
    }

    private void sendToDlq(KafkaCancelledDto dto) {
        kafkaTemplate.send(KafkaTopicsConfig.TICKET_CANCELLED_DLQ, dto);
    }
}
