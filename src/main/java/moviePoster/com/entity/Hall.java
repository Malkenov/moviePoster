package moviePoster.com.entity;


import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "hall")
public class Hall extends BaseEntity {

    @Column(name = "name",unique = true)
    private String name;


    @ManyToOne
    @JoinColumn(name = "cinema_id",unique = true)
    private Cinema cinema;
}
