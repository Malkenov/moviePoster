package moviePoster.com.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cinema")
public class Cinema extends BaseEntity {

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "address",nullable = false)
    private String address;


    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "cinemas")
    private List<Session> sessionList = new ArrayList<>();

    @OneToMany(mappedBy = "cinema")
    private List<FavouritesCinema> favouritesCinemas;

    @OneToMany(mappedBy = "cinema")
    private List<Hall> hall;
}
