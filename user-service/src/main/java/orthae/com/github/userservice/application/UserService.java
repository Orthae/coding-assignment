package orthae.com.github.userservice.application;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import orthae.com.github.userservice.domain.*;
import orthae.com.github.userservice.web.model.LoginCommand;
import orthae.com.github.userservice.web.model.SignupCommand;

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

    public void createUser(SignupCommand command) {
        if(userRepository.existsByUsername(command.username())) {
            throw new UserAlreadyExists();
        }

        var role = switch (command.role()) {
            case ADMIN -> Role.ADMIN;
            case USER -> Role.USER;
        };

        userRepository.save(User.of(command.username(), passwordEncoder.encode(command.password()), role));
    }

    public Jwt createToken(LoginCommand command) {
        var user = userRepository.findByUsername(command.username()).orElseThrow(InvalidCredentials::new);
        if(!passwordEncoder.matches(command.password(), user.getPassword())) {
          throw new InvalidCredentials();
        }

        return tokenFactory.createToken(user);
    }
}
