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
import java.util.Collection;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "userSession")
public class UserSessionEntity extends BaseEntity implements UserDetails {

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


    @Column(name = "is_phone_verified", nullable = false)
    private boolean isPhoneVerified;

    @Column(name = "is_email_verified",nullable = false)
    private boolean isEmailVerified;

    @Column(name = "is_active")
    @ColumnDefault("true")
    private boolean isActive;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private CityEntity city;

    @OneToMany(mappedBy = "user")
    private List<FavouritesCinemaEntity> favouritesCinemas;

    @OneToMany(mappedBy = "user")
    private List<TicketEntity> tickets;

    @OneToMany(mappedBy = "user")
    private List<ReviewEntity> reviews;

    @OneToMany(mappedBy = "user")
    private List<AuthSessions> authSessions;

    @OneToMany(mappedBy = "user")
    private List<OtpCodeEntity> otpCode;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "users")
    private List<TokenEntity> tokenEntities;

    @OneToMany(mappedBy = "user")
    private List<SeatEntity> seatEntity;


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
