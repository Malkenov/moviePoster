package moviePoster.com.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "Пользователи")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(length = 2000)
    private String description;

    @Column(name = "Продолжительность")
    private Integer duration;

    @Column(name = "Рейтинг")
    private Double ageRating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(title, movie.title) && Objects.equals(description, movie.description) && Objects.equals(duration, movie.duration) && Objects.equals(ageRating, movie.ageRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, duration, ageRating);
    }
}
