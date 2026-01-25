package moviePoster.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
@Table(name = "Movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;


    // автоматический устанавливается при создании
    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    // срабатывает обновление записи
    @PreUpdate
    protected void onUpdate(){
        this.modifiedAt = LocalDateTime.now();
    }



    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "movies")
    private List<Session> sessionList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "events_id")
    private Events events;

    @OneToOne(mappedBy = "movie")
    private Review review;
}
