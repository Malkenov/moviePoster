package moviePoster.com.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import moviePoster.com.domain.entity.Token.TokenEntity;
import moviePoster.com.dto.enums.Role;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user_session")
public class UserSessionEntity extends BaseEntity implements UserDetails {

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotBlank
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_verified", nullable = false)
    private boolean phoneVerified;

    @Column(name = "email_verified", nullable = false)
    private boolean emailVerified;

    @Column(name = "active")
    @ColumnDefault("true")
    @Builder.Default
    private boolean active = true;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private CityEntity city;

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<FavouritesCinemaEntity> favouritesCinemas = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<TicketEntity> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<ReviewEntity> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<AuthSessions> authSessions = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<OtpCodeEntity> otpCode = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "users")
    @Builder.Default
    private List<TokenEntity> tokenEntities = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<SeatEntity> seatEntity = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() { return email; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
