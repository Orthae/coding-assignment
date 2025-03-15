package orthae.com.github.userservice.application;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import orthae.com.github.userservice.application.factory.TokenFactory;
import orthae.com.github.userservice.application.factory.UserFactory;
import orthae.com.github.userservice.application.factory.UserMapper;
import orthae.com.github.userservice.application.model.TokenModel;
import orthae.com.github.userservice.application.model.UserModel;
import orthae.com.github.userservice.domain.*;
import orthae.com.github.userservice.application.model.CreateTokenCommand;
import orthae.com.github.userservice.application.model.CreateUserCommand;

@Service
public class UserService {
    private final UserFactory userFactory;
    private final TokenFactory tokenFactory;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserFactory userFactory, UserRepository userRepository, TokenFactory tokenFactory, PasswordEncoder passwordEncoder) {
        this.userFactory = userFactory;
        this.userRepository = userRepository;
        this.tokenFactory = tokenFactory;
        this.passwordEncoder = passwordEncoder;
    }

    public UserModel createUser(CreateUserCommand command) {
        if(userRepository.existsByUsername(command.getUsername())) {
            throw new UserAlreadyExistsException();
        }

        var user = userFactory.create(command);
        userRepository.save(user);

        return UserMapper.toModel(user);
    }

    public TokenModel createToken(CreateTokenCommand command) {
        var user = userRepository.findByUsername(command.getUsername()).orElseThrow(InvalidCredentialsException::new);
        if(!passwordEncoder.matches(command.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return tokenFactory.createToken(user);
    }
}
