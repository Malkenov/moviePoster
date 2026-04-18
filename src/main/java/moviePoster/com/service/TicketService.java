package moviePoster.com.service;

import lombok.RequiredArgsConstructor;
import moviePoster.com.domain.entity.TicketEntity;
import moviePoster.com.dto.kafka.dto.KafkaPurchasedDto;
import moviePoster.com.repository.TicketRepository;
import moviePoster.com.service.producer.KafkaPurchasedProducer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {
    // Сервис достаёт данные из БД и собирает DTO

    private final TicketRepository ticketRepository;
    private final KafkaPurchasedProducer purchasedProducer;

    public void purchaseTicket(Long ticketId){
        TicketEntity ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Билет не найден!"));


    KafkaPurchasedDto dto = new KafkaPurchasedDto();
    dto.setTicketId(ticket.getId());
    dto.setUserEmail(ticket.getUser().getEmail());
    dto.setUserName(ticket.getUser().getName());
    dto.setMovieName(ticket.getSession().getMovies().getName());

    purchasedProducer.send(dto);
}}
