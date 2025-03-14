package orthae.com.github.taskservice.domain;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.UUID;

public record AuthenticatedUser(UUID id, String username, Role role) {
    public static AuthenticatedUser from(JwtAuthenticationToken jwt) {
        return new AuthenticatedUser(
                UUID.fromString(jwt.getToken().getClaimAsString("id")),
                jwt.getToken().getClaimAsString("username"),
                Role.valueOf(jwt.getToken().getClaimAsStringList("roles").getFirst())
        );
    }
}
