package moviePoster.com.entity;


import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Цена")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Цена")
    private Double amount;

    @Column(name = "Категория")
    private String category;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;
}
