package moviePoster.com.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moviePoster.com.service.consumer.KafkaMovieReminderConsumer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieReminderScheduler {

    private final KafkaMovieReminderConsumer movieReminderConsumer;

    @Scheduled(fixedRate = 60000)
    public void checkReminders() {
        log.info("Проверка напоминаний о сеансах...");
        movieReminderConsumer.sendReminders();
    }
}