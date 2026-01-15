package moviePoster.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Тур компании")
public class TourCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Название")
    private String name;

    @Column(name = "Описание")
    private String title;

    @Column(name = "Город")
    private String city;

    @Column(name = "Время работы")
    private LocalTime localTime;

    @OneToMany(mappedBy = "tourCompany")
    private List<Tours> tourList;
}
