package orthae.com.github.taskservice.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
public class AuthenticatedUser {
    private static final Logger log = LoggerFactory.getLogger(AuthenticatedUser.class);

    private final UUID id;
    private final String username;
    private final Role role;


    public static AuthenticatedUser from(JwtAuthenticationToken jwt) {
        try {
            var id = Optional.ofNullable(jwt.getToken().getClaimAsString("id"))
                    .map(UUID::fromString)
                    .orElseThrow(() -> new IllegalArgumentException("Missing id claim in token."));

            var username = Optional.ofNullable(jwt.getToken().getClaimAsString("username"))
                    .orElseThrow(() -> new IllegalArgumentException("Missing username claim in token."));

            var roles = Optional.ofNullable(jwt.getToken().getClaimAsStringList("roles"))
                    .orElseThrow(() -> new IllegalArgumentException("Missing roles claim in token."));

            if (roles.size() != 1) {
                throw new IllegalArgumentException("Invalid number of roles in token.");
            }

            return AuthenticatedUser.builder()
                    .id(id)
                    .username(username)
                    .role(Role.valueOf(roles.getFirst()))
                    .build();

        } catch (Exception exception) {
            log.error("Failed to convert JWT to AuthenticatedUser.", exception);
            throw new InvalidTokenFormat("Invalid token format.", exception);
        }
    }
}
