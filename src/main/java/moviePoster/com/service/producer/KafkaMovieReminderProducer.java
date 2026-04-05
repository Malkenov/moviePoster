package moviePoster.com.service.producer;


import moviePoster.com.dto.kafka.dto.MovieReminderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaMovieReminderProducer {

    private final KafkaTemplate<String, MovieReminderDto> kafkaTemplate;

    @Scheduled(fixedRate = 60000) // Каждую минуты провераяет - есть ли сеансы за 2 часа
    public void sendReminders(){
        // надо доделать с БД,где будет с БД делать запросы на все фильмы за 2 часа от настоящего времени
        System.out.println("Проверка сеансов за 2 часа");
    }

    public void send(MovieReminderDto dto){
        kafkaTemplate.send("movie-reminder",dto);
        System.out.println("Отправлено " + dto);
    }
}

