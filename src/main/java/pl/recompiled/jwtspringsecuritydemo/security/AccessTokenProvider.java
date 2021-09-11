package pl.recompiled.jwtspringsecuritydemo.security;

import org.springframework.security.core.Authentication;

import java.util.List;

public interface AccessTokenProvider {

    String getAccessToken(String userId, List<String> roles);

    Authentication extractAuthentication(String token);

}
