package moviePoster.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Театры")
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Название")
    private String name;

    @Column(name = "Описание")
    private String title;

    @Column(name = "Адрес")
    private String address;

    @Column(name = "Город")
    private String city;

    @Column(name = "Время проведение")
    private LocalDateTime working_hours;

    @OneToOne(mappedBy = "theatre")
    private List<Performance> performanceList;
}
