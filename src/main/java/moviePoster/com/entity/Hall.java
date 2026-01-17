package moviePoster.com.entity;


import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Залы")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Название зала")
    private String name;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;
}
