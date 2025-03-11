package orthae.com.github.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class User {
    private UUID id;
    private String username;
    private String password;
    private Role role;

    public static User of(String username, String password, Role role) {
        return new User(UUID.randomUUID(), username, password, role);
    }
}
