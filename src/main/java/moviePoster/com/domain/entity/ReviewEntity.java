package moviePoster.com.domain.entity;


import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "review")
public class ReviewEntity extends BaseEntity {

    @Column(name = "grade",nullable = false)
    private int grade;

    @Column(name = "title",nullable = false)
    private String title;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserSessionEntity user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;
}
