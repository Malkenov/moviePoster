package moviePoster.com.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ticket")
public class Ticket extends BaseEntity {

    @Column(name = "row",unique = true,nullable = false)
    @Positive
    private int row;

    @Column(name = "place",unique = true,nullable = false)
    @Positive
    private int place;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserSession user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "session_id",unique = true)
    private Session session;

    @OneToMany(mappedBy = "ticket")
    private List<Price> price;
}
