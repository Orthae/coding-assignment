package orthae.com.github.userservice.application.factory;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;
import orthae.com.github.userservice.application.model.TokenModel;
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

    public TokenModel createToken(User user) {
        var now = clock.instant();
            var claims = JwtClaimsSet.builder()
                    .issuer("user-service")
                    .issuedAt(now)
                    .expiresAt(now.plus(tokenProperties.getExpiration()))
                    .claim("id", user.getId())
                    .claim("username", user.getUsername())
                    .claim("roles", List.of(user.getRole()))
                    .build();

            var token = jwtEncoder.encode(JwtEncoderParameters.from(claims));

            return TokenModel.builder()
                    .issuedAt(now)
                    .expiresAt(claims.getExpiresAt())
                    .token(token.getTokenValue())
                    .build();
    }
}
