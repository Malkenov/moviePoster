package moviePoster.com.controller;


import moviePoster.com.dto.kafka.dto.*;
import moviePoster.com.service.NewMovieReleasedService;
import moviePoster.com.service.TicketService;
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
    private final TicketService ticketService;
    private final NewMovieReleasedService newMovieReleasedService;


    @PostMapping("/ticket-purchased/{ticketId}")
    public ResponseEntity<String> sendTicket(@PathVariable Long ticketId){
        ticketService.purchaseTicked(ticketId);
        return ResponseEntity.ok("Сообщение отправлно!");
    }

    @PostMapping("/ticket-cancelled/{ticketId}")
    public ResponseEntity<String> sendCancelled(@PathVariable Long ticketId){
        ticketService.purchaseTicked(ticketId);
        return ResponseEntity.ok("Возврат в обработке!");
    }

    @PostMapping("/new-movie-released/{movieId}")
    public ResponseEntity<String> sendNewMovieReleased(@PathVariable Long movieId){
        newMovieReleasedService.notifyNewMovie(movieId);
        return ResponseEntity.ok("Отпрвлено сообщение о новом релизе");
    }


}

