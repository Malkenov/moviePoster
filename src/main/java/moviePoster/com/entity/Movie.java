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
@Table(name = "Пользователи")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Название фильма")
    private String name;

    @Column(name = "Описание")
    private String title;

    @Column(name = "Премьера")
    private Boolean isPremiere;

    @Column(name = "Возрастное ограничение")
    private int age_limit;

    @Column(name = "Продолжительность")
    private Integer duration;

    @Column(name = "Рейтинг")
    private Double ageRating;

    @Column(name = "Язык")
    private String language;

    @Column(name = "Создание", updatable = false)
    private LocalDateTime created_at;

    @Column(name = "Обновление")
    private LocalDateTime modified_at;


    // автоматический устанавливается при создании
    @PrePersist
    protected void onCreate(){
        this.created_at = LocalDateTime.now();
        this.modified_at = LocalDateTime.now();
    }

    // срабатывает обновление записи
    @PreUpdate
    protected void onUpdate(){
        this.modified_at = LocalDateTime.now();
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
