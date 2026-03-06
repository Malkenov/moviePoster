package moviePoster.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "movie")
public class MovieEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "isPremiere")
    private Boolean isPremiere;

    @Column(name = "age_limit",nullable = false)
    private int ageLimit;

    @Column(name = "duration",nullable = false)
    private Integer duration;

    @Column(name = "age_rating",nullable = false)
    private Double ageRating;

    @Column(name = "language",nullable = false)
    private String language;


    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<GenreEntity> genres = new HashSet<>();

    @OneToMany(mappedBy = "movies")
    private List<SessionEntity> sessionList = new ArrayList<>();


    @OneToOne(mappedBy = "movie")
    private ReviewEntity review;
}
