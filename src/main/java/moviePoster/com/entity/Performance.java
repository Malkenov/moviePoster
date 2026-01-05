package moviePoster.com.entity;

import com.fasterxml.jackson.annotation.JacksonInject;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Спектакль")
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Название")
    private String name;

    @Column(name = "Описание")
    private String title;

    @Column(name = "Возрастное ограничение")
    private int age_limit;

    @Column(name = "Место проведение")
    private String theatre_type;

    @ManyToOne
    @JoinColumn(name = "events_id")
    private Events events;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;
}
