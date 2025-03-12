package orthae.com.github.userservice.application;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import orthae.com.github.userservice.domain.*;

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
        if(userRepository.existsByUsername(username)) {
            throw new UserAlreadyExists();
        }

        userRepository.save(User.of(username, passwordEncoder.encode(password), role));
    }

    public Jwt createToken(String username, String password) {
        var user = userRepository.findByUsername(username).orElseThrow(InvalidCredentials::new);
        if(!passwordEncoder.matches(password, user.getPassword())) {
          throw new InvalidCredentials();
        }

        return tokenFactory.createToken(user);
    }
}
