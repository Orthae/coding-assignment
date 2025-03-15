package orthae.com.github.userservice.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class UserModel {
    private final UUID id;
    private final String username;
    private final RoleModel role;
}
