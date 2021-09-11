package pl.recompiled.jwtspringsecuritydemo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import pl.recompiled.jwtspringsecuritydemo.user.AppUser;
import pl.recompiled.jwtspringsecuritydemo.user.AppUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AccessTokenAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final AccessTokenProvider accessTokenProvider;
    private final AppUserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) {
        final AppUser user = (AppUser) auth.getPrincipal();
        final String accessToken = accessTokenProvider.getAccessToken(
                userService.loadUserByUsername(user.getUsername()).getId().toString(),
                user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()));
        response.setHeader("access_token", accessToken);
    }

}
