package moviePoster.com.service.producer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moviePoster.com.config.KafkaTopicsConfig;
import moviePoster.com.dto.kafka.dto.NewMovieReleasedDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class NewMovieReleasedProducer {

    private final KafkaTemplate<String, NewMovieReleasedDto> kafkaTemplate;

    public void send(@Valid NewMovieReleasedDto dto){
        kafkaTemplate.send(KafkaTopicsConfig.NEW_MOVIE_RELEASED,dto);
        log.info("Отправлено {}", dto);
    }
}
