package moviePoster.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Концерт")
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Имя")
    private String name;

    @Column(name = "Описание")
    private String title;

    @Column(name = "Возрастное ограничение")
    private int age_limit;

    @ManyToOne
    @JoinColumn(name = "events_id")
    private Events events;
}
