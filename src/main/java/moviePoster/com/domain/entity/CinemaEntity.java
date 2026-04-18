package moviePoster.com.domain.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "cinema")
public class CinemaEntity extends BaseEntity {

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "address",nullable = false)
    private String address;


    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;

    @OneToMany(mappedBy = "cinema")
    private List<SessionEntity> sessionList = new ArrayList<>();

    @OneToMany(mappedBy = "cinema")
    private List<FavouritesCinemaEntity> favouritesCinemas;

    @OneToMany(mappedBy = "cinema")
    private List<HallEntity> hall;
}
