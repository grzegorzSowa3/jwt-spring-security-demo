package pl.recompiled.jwtspringsecuritydemo.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import pl.recompiled.jwtspringsecuritydemo.security.AccessTokenProvider;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAccessTokenProvider implements AccessTokenProvider {

    private final JwtAccessTokenProperties properties;
    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    public JwtAccessTokenProvider(JwtAccessTokenProperties properties) {
        this.properties = properties;
        this.algorithm = Algorithm.HMAC256(properties.getSecret().getBytes(StandardCharsets.UTF_8));
        this.verifier = JWT.require(algorithm).build();
    }

    @Override
    public String getAccessToken(String userId, List<String> roles) {
        return JWT.create()
                .withSubject(userId)
                .withExpiresAt(new Date(System.currentTimeMillis() + properties.getTokenLifespanSeconds() * 1000))
                .withClaim("roles", roles)
                .sign(algorithm);
    }

    @Override
    public UsernamePasswordAuthenticationToken extractAuthentication(String token) {
        DecodedJWT jwt = verifier.verify(token);
        return new UsernamePasswordAuthenticationToken(
                jwt.getSubject(),
                null,
                jwt.getClaim("roles").asList(String.class).stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );
    }

}
