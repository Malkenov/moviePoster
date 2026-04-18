package moviePoster.com.domain.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movies;
}
