package pl.recompiled.jwtspringsecuritydemo.security.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "access-token.jwt")
class JwtAccessTokenProperties {

    private String secret;
    private Integer tokenLifespanSeconds;

}