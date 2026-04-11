package moviePoster.com.service.producer;

import lombok.RequiredArgsConstructor;
import moviePoster.com.config.KafkaTopicsConfig;
import moviePoster.com.dto.kafka.dto.NewMovieReleasedDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewMovieReleasedProducer {

    private final KafkaTemplate<String, NewMovieReleasedDto> kafkaTemplate;

    public void send(NewMovieReleasedDto dto){
        kafkaTemplate.send(KafkaTopicsConfig.NEW_MOVIE_RELEASED,dto);
        System.out.println("Отправлено " + dto);
    }
}
