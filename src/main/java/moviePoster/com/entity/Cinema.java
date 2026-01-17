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
@Table(name = "Кинотеатры")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Название")
    private String name;

    @Column(name = "Город")
    private String city;

    @Column(name = "Адрес")
    private String address;


    @OneToMany(mappedBy = "cinemas")
    private List<Session> sessionList = new ArrayList<>();

    @OneToMany(mappedBy = "cinema")
    private List<FavouritesCinema> favouritesCinemas;

}
