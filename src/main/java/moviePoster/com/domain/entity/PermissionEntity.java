package moviePoster.com.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "permission")
public class PermissionEntity extends BaseEntity {

    @Column(name = "name",nullable = false)
    private String name;


    @ManyToMany(mappedBy = "permission")
    private List<RoleEntity> role;
}
