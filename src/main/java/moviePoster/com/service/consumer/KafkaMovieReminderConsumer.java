package moviePoster.com.service.consumer;

import lombok.RequiredArgsConstructor;
import moviePoster.com.domain.entity.SessionEntity;
import moviePoster.com.domain.entity.TicketEntity;
import moviePoster.com.dto.kafka.dto.MovieReminderDto;
import moviePoster.com.repository.SessionRepository;
import moviePoster.com.service.producer.KafkaMovieReminderProducer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaMovieReminderConsumer {
    // напоминание о сеансе

    private final SessionRepository sessionRepository;
    private final KafkaMovieReminderProducer producer;

    public void sendReminders() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime from = now.plusHours(2).minusMinutes(1);
        LocalDateTime to = now.plusHours(2).plusMinutes(1);

        List<SessionEntity> sessions = sessionRepository.findByStartTimeBetween(from, to);

        for (SessionEntity session : sessions) {
            if (session.getTicket() == null) continue;      // в слуачае остуствие списка билетов

            for (TicketEntity ticket : session.getTicket()) {
                if (ticket.getUser() == null) continue;     // если у билета нет пользователя

                MovieReminderDto dto = new MovieReminderDto();
                dto.setUserEmail(ticket.getUser().getEmail());
                dto.setUserName(ticket.getUser().getName());
                dto.setMovieName(session.getMovies().getName());
                dto.setShowTime(session.getStartTime());
                dto.setSeatNumber(String.valueOf(ticket.getPlace()));
                dto.setCinemaName(session.getCinemas().getName());

                producer.send(dto);
            }
        }
    }
}