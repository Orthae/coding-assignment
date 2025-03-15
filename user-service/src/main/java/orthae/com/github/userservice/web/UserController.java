package orthae.com.github.userservice.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orthae.com.github.userservice.application.model.TokenModel;
import orthae.com.github.userservice.application.model.UserModel;
import orthae.com.github.userservice.application.UserService;
import orthae.com.github.userservice.application.model.CreateTokenCommand;
import orthae.com.github.userservice.application.model.CreateUserCommand;


@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/signup", produces = MediaType.PUBLIC_JSON_V1, consumes = MediaType.PUBLIC_JSON_V1)
    public ResponseEntity<UserModel> signup(@Valid @RequestBody CreateUserCommand command) {
        var user = userService.createUser(command);

        return ResponseEntity.status(HttpStatus.OK)
                .body(user);
    }

    @PostMapping(path = "/login", produces = MediaType.PUBLIC_JSON_V1, consumes = MediaType.PUBLIC_JSON_V1)
    public ResponseEntity<TokenModel> login(@Valid @RequestBody CreateTokenCommand command) {
        var token = userService.createToken(command);

        return ResponseEntity.status(HttpStatus.OK)
                .body(token);
    }
}
