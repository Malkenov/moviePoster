package moviePoster.com.service.producer;

import lombok.RequiredArgsConstructor;
import moviePoster.com.dto.kafka.dto.NewMovieReleasedDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewMovieReleasedProducer {

    private final KafkaTemplate<String, NewMovieReleasedDto> kafkaTemplate;

    public void send(NewMovieReleasedDto dto){
        kafkaTemplate.send("new-movie-released",dto);
        System.out.println("Отправлено " + dto);
    }
}
