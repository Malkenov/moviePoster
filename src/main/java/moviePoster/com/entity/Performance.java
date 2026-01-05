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
@Table(name = "Театр")
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


    @ManyToOne
    @JoinColumn(name = "events_id")
    private Events events;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;
}
