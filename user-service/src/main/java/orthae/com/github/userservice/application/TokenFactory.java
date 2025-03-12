package orthae.com.github.userservice.application;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;
import orthae.com.github.userservice.domain.User;
import orthae.com.github.userservice.infrastructure.config.TokenProperties;

import java.time.Clock;
import java.util.List;

@Component
public class TokenFactory {
    private final JwtEncoder jwtEncoder;
    private final TokenProperties tokenProperties;
    private final Clock clock;

    public TokenFactory(JwtEncoder jwtEncoder, TokenProperties tokenProperties, Clock clock) {
        this.jwtEncoder = jwtEncoder;
        this.tokenProperties = tokenProperties;
        this.clock = clock;
    }

    public Jwt createToken(User user) {
            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("user-service")
                    .expiresAt(clock.instant().plus(tokenProperties.getExpiration()))
                    .claim("id", user.getId())
                    .claim("username", user.getUsername())
                    .claim("roles", List.of(user.getRole()))
                    .build();

            return jwtEncoder.encode(JwtEncoderParameters.from(claims));
    }
}
