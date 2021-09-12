package pl.recompiled.jwtspringsecuritydemo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddExampleUserRunner implements CommandLineRunner {

    private final AppUserService userService;

    @Override
    public void run(String... args) {
        userService.createUser("user", "pass");
        userService.createUser("user2", "pass");
        userService.createAdmin("admin", "pass");
    }

}
