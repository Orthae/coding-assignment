package orthae.com.github.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class User {
    private final UUID id;
    private final String username;
    private final String password;
    private final Role role;
}
