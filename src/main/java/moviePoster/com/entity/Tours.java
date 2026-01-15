package moviePoster.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Туры")
public class Tours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Название")
    private String name;

    @Column(name = "Описание")
    private String title;

    @Column(name = "Возрастное ограничение")
    private int age_limit;

    @Column(name = "Цена")
    private Double price;

    @Column(name = "Дата")
    private LocalDateTime dataTime;


    @ManyToOne
    @JoinColumn(name = "events_id")
    private Events events;

    @ManyToOne
    @JoinColumn(name = "tourCompany_id")
    private TourCompany tourCompany;

}
