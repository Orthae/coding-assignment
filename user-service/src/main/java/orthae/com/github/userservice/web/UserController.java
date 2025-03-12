package orthae.com.github.userservice.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import orthae.com.github.userservice.application.UserService;
import orthae.com.github.userservice.domain.Role;


@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/signup", produces = MediaType.PUBLIC_JSON_V1, consumes = MediaType.PUBLIC_JSON_V1)
    public ResponseEntity<Void> signup(@Valid @RequestBody SignupCommand command) {
        var role = switch (command.role()) {
            case ADMIN -> Role.ADMIN;
            case USER -> Role.USER;
        };
        userService.createUser(command.username(), command.password(), role);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping(path = "/login", produces = MediaType.PUBLIC_JSON_V1, consumes = MediaType.PUBLIC_JSON_V1)
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginCommand command) {
        var token = userService.createToken(command.username(), command.password()).getTokenValue();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new LoginResponse(token));
    }
}
