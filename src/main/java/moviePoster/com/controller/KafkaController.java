package moviePoster.com.controller;


import moviePoster.com.dto.kafka.dto.*;
import moviePoster.com.service.producer.*;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/notifications")
@RestController
public class KafkaController {

    private final KafkaPurchasedProducer kafkaPurchasedProducer;
    private final KafkaCancelledProducer kafkaCancelledProducer;
    private final NewMovieReleasedProducer newMovieReleasedProducer;
    private final KafkaMovieReminderProducer kafkaMovieReminderProducer;
    private final SeatExpiredProducer seatExpiredProducer;

    @PostMapping("/ticket-purchased")
    public ResponseEntity<String> sendTicket(@RequestBody KafkaPurchasedDto dto){
        kafkaPurchasedProducer.send(dto);
        return ResponseEntity.ok("Сообщение отправлно!");
    }

    @PostMapping("/ticket-cancelled")
    public ResponseEntity<String> sendCancelled(@RequestBody KafkaCancelledDto dto){
        kafkaCancelledProducer.send(dto);
        return ResponseEntity.ok("Возврат в обработке!");
    }

    @PostMapping("/new-movie-released")
    public ResponseEntity<String> sendNewMovieReleased(@RequestBody NewMovieReleasedDto dto){
        newMovieReleasedProducer.send(dto);
        return ResponseEntity.ok("Отпрвлено сообщение о новом релизе");
    }

    @PostMapping("/movie-reminder")
    public ResponseEntity<String> sendMovieReminder(@RequestBody MovieReminderDto dto){
        kafkaMovieReminderProducer.sendReminders();
        return ResponseEntity.ok("Отправлено напоминание о сеансе за 2 час до начало");
    }

}

