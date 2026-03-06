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
@Table(name = "city")
public class CityEntity extends BaseEntity {

    @Column(name = "name",nullable = false)
    private String name;


    @OneToMany(mappedBy = "city")
    private UserSessionEntity user;

    @OneToMany(mappedBy = "city")
    private List<CinemaEntity> cinemas;
}
