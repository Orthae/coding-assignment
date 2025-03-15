package orthae.com.github.userservice.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
public class TokenModel {
    private final Instant issuedAt;
    private final Instant expiresAt;
    private final String token;
}
