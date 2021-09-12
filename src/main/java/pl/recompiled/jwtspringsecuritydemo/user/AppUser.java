package pl.recompiled.jwtspringsecuritydemo.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @Convert(converter = AuthoritiesToStringConverter.class)
    private Set<Authority> authorities;

    public static AppUser newInstance(String username, String password, Authority... authorities) {
        final AppUser user = new AppUser();
        user.username = username;
        user.password = password;
        user.authorities = Set.of(authorities);
        return user;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
