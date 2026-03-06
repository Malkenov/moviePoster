package moviePoster.com.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity {

    @Column(name = "name",nullable = false)
    private String name;

}
