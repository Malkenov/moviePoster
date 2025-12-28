package moviePoster.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Column(name = "Кино")
    private String movie;

    @Column(name = "Кинотеатр")
    private String cinema;

    @Column(name = "Начало сеанса")
    private Integer startTime;

    @Column(name = "Цена",precision = 10, scale = 2)
    private BigDecimal price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(movie, session.movie) && Objects.equals(cinema, session.cinema) && Objects.equals(startTime, session.startTime) && Objects.equals(price, session.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, cinema, startTime, price);
    }

}
