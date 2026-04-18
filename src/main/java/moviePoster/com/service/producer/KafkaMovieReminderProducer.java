package moviePoster.com.service.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moviePoster.com.config.KafkaTopicsConfig;
import moviePoster.com.dto.kafka.dto.MovieReminderDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMovieReminderProducer {
    // напоминание о сеансе

    private final KafkaTemplate<String, MovieReminderDto> kafkaTemplate;

    public void send(MovieReminderDto dto) {
        kafkaTemplate.send(KafkaTopicsConfig.MOVIE_REMINDER, dto);
        log.info("Напоминание отправлено: {}", dto);

    }
}
