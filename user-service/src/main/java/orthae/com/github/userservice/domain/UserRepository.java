package orthae.com.github.userservice.domain;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
    void save(User user);
}
