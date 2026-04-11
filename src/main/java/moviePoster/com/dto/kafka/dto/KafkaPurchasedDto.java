package moviePoster.com.dto.kafka.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaPurchasedDto {

    private String userEmail;
    private String userName;
    private String movieName;
    private Long ticketId;
}

