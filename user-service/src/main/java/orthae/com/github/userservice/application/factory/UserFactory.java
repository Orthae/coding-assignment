package orthae.com.github.userservice.application.factory;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import orthae.com.github.userservice.domain.User;
import orthae.com.github.userservice.application.model.CreateUserCommand;

import java.util.UUID;

@Component
public class UserFactory {
    private final PasswordEncoder passwordEncoder;

    public UserFactory(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User create(CreateUserCommand command) {
        return User.builder()
                .id(UUID.randomUUID())
                .username(command.getUsername())
                .password(passwordEncoder.encode(command.getPassword()))
                .role(RoleMapper.toDomain(command.getRole()))
                .build();
    }
}
