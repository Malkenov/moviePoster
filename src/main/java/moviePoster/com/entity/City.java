package moviePoster.com.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "city")
public class City extends BaseEntity {

    @Column(name = "name",nullable = false)
    private String name;


    @OneToMany(mappedBy = "city")
    private UserSession user;

    @OneToMany(mappedBy = "city")
    private List<Cinema> cinemas;
}
