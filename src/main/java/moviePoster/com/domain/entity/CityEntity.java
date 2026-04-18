package moviePoster.com.domain.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "city")
public class CityEntity extends BaseEntity {

    @Column(name = "name",nullable = false)
    private String name;


    @OneToMany(mappedBy = "city")
    private List<UserSessionEntity> user;

    @OneToMany(mappedBy = "city")
    private List<CinemaEntity> cinemas;
}
