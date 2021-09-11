package pl.recompiled.jwtspringsecuritydemo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AppUser createUser(String username, String password) {
        return repository.save(AppUser.newInstance(username, passwordEncoder.encode(password), Authority.USER));
    }

    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with such username!"));
    }
}
