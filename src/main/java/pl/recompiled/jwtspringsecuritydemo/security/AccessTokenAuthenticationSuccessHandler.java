package pl.recompiled.jwtspringsecuritydemo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AccessTokenAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final AccessTokenProvider accessTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) {
        final User user = (User) auth.getPrincipal();
        final String accessToken = accessTokenProvider.getAccessToken(
                user.getUsername(),
                user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()));
        response.setHeader("access_token", accessToken);
    }

}
