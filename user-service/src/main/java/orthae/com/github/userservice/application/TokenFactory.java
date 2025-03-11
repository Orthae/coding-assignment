package orthae.com.github.userservice.application;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;
import orthae.com.github.userservice.domain.User;

@Component
public class TokenFactory {
    private final JwtEncoder jwtEncoder;

    public TokenFactory(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public Jwt createToken(User user) {
            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .claim("id", user.getId())
                    .claim("username", user.getUsername())
                    .claim("role", user.getRole())
                    .build();

            return jwtEncoder.encode(JwtEncoderParameters.from(claims));
    }
}
