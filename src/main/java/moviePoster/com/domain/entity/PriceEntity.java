package moviePoster.com.domain.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "price")
public class PriceEntity extends BaseEntity {

    @Column(name = "amount",nullable = false)
    @PositiveOrZero
    private Double amount;

    @Column(name = "category",nullable = false)
    private String category;


    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private TicketEntity ticket;
}
