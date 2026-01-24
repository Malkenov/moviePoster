package moviePoster.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Жанр")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Имя",unique = true,nullable = false)
    private String name;

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


    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies = new HashSet<>();

    @OneToMany(mappedBy = "genre")
    private List<Performance> performanceList;
}
