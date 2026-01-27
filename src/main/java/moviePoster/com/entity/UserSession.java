package moviePoster.com.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import moviePoster.com.entity.Token.TokenEntity;
import moviePoster.com.enums.Role;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "UserSession")
public class UserSession implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name",nullable = false)
    private String name;

    @NotNull
    @Column(name = "date_of_birth",nullable = false)
    private LocalDate dateOfBirth;

    @NotNull
    @Column(name = "phone",nullable = false)
    private String phone;

    @NotBlank
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(name = "password",nullable = false)
    private String password;

    @NotNull
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @NotNull
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


    @Column(name = "is_phone_verified", nullable = false)
    private boolean isPhoneVerified;

    @Column(name = "is_email_verified",nullable = false)
    private boolean isEmailVerified;

    @Column(name = "is_active")
    @ColumnDefault("true")
    private boolean isActive;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "user")
    private List<FavouritesCinema> favouritesCinemas;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @OneToMany(mappedBy = "user")
    private List<AuthSessions> authSessions;

    @OneToMany(mappedBy = "user")
    private List<OtpCode> otpCode;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "users")
    private List<TokenEntity> tokenEntities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }




    @Override
    public String getUsername() {return email;}

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return true;}
}
