package moviePoster.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "permission")
public class Permission extends BaseEntity {

    @Column(name = "name",nullable = false)
    private String name;


    @ManyToMany(mappedBy = "permission")
    private List<Role> role;
}
