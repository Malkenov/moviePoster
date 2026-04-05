package moviePoster.com.dto.kafka.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaCancelledDto {

    private String ticketId;           // номер билета
    private Long userId;               // кто отменил

    private String userName;           // имя пользователя
    private String userEmail;          // email пользователя

    private String movieName;          // название фильма
    private LocalDateTime showTime;    // время и дата сеанса
    private String seatNumber;         // место

    private BigDecimal refundAmount;   // сумма возврата
    private String paymentId;          // Id оригинального платежа

    private LocalDateTime cancelledAt; // время отмена билета
    private String cancellationReason; // причина отмена билета
}

