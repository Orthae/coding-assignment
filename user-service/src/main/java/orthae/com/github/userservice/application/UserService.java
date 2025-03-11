package orthae.com.github.userservice.application;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import orthae.com.github.userservice.domain.Role;
import orthae.com.github.userservice.domain.User;
import orthae.com.github.userservice.domain.UserRepository;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenFactory tokenFactory;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, TokenFactory tokenFactory) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenFactory = tokenFactory;
    }


    public void createUser(String username, String password, Role role) {
        userRepository.save(User.of(username, passwordEncoder.encode(password), role));
    }

    public Jwt createToken(String username, String password) {
        var user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(password, user.getPassword())) {
            return tokenFactory.createToken(user);
        } else {
            System.out.println("User not authenticated");
        }

        return null;
    }
}
