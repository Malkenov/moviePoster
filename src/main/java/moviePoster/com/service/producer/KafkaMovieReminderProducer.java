package moviePoster.com.service.producer;


import lombok.extern.slf4j.Slf4j;
import moviePoster.com.config.KafkaTopicsConfig;
import moviePoster.com.dto.kafka.dto.MovieReminderDto;
import lombok.RequiredArgsConstructor;
import moviePoster.com.domain.entity.SessionEntity;
import moviePoster.com.domain.entity.TicketEntity;
import moviePoster.com.repository.SessionRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMovieReminderProducer {

    private final KafkaTemplate<String, MovieReminderDto> kafkaTemplate;
    private final SessionRepository sessionRepository;

    @Scheduled(fixedRate = 60000) // Каждую минуты провераяет - есть ли сеансы за 2 часа
    public void sendReminders() {

        LocalDateTime now = LocalDateTime.now();

        LocalDateTime from = now.plusHours(2).minusMinutes(1);
        LocalDateTime to = now.plusHours(2).plusMinutes(1);

        List<SessionEntity> sessions = sessionRepository.findByStartTimeBetween(from, to);

        for (SessionEntity session : sessions) {

            if (session.getTicket() == null) continue; // в слуачае остуствие списка билетов

            for (TicketEntity ticket : session.getTicket()) {

                if (ticket.getUser() == null) continue; // если у билета нет пользователя

                MovieReminderDto dto = new MovieReminderDto();
                dto.setUserEmail(ticket.getUser().getEmail());
                dto.setUserName(ticket.getUser().getName());
                dto.setMovieName(session.getMovies().getName());
                dto.setShowTime(session.getStartTime());
                dto.setSeatNumber(String.valueOf(ticket.getPlace()));
                dto.setCinemaName(session.getCinemas().getName());

                kafkaTemplate.send(KafkaTopicsConfig.MOVIE_REMINDER, dto);
                log.info("Напоминание отправлено {}", ticket.getUser().getEmail());
            }
        }
    }
}

