package moviePoster.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Сеанс")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Начало сеанса")
    private Integer startTime;


    @ManyToOne(optional = false)
    @JoinColumn(name = "cinema_id")
    private Cinema cinemas;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id")
    private Movie movies;

    @OneToMany(mappedBy = "session")
    private List<Price> price;

    @OneToOne(mappedBy = "session")
    private Ticket ticket;

}
