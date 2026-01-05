package moviePoster.com.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Концертные площадки")
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Название")
    private String name;

    @Column(name = "Описание")
    private String title;

    @Column(name = "Адрес")
    private String address;

    @Column(name = "Город")
    private String city;

    @OneToMany(mappedBy = "venue")
    private List<Concert> concertList;
}
