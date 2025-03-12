package orthae.com.github.userservice.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import orthae.com.github.userservice.application.UserService;
import orthae.com.github.userservice.web.model.LoginCommand;
import orthae.com.github.userservice.web.model.LoginResponse;
import orthae.com.github.userservice.web.model.SignupCommand;


@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/signup", produces = MediaType.PUBLIC_JSON_V1, consumes = MediaType.PUBLIC_JSON_V1)
    public ResponseEntity<Void> signup(@Valid @RequestBody SignupCommand command) {
        userService.createUser(command);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping(path = "/login", produces = MediaType.PUBLIC_JSON_V1, consumes = MediaType.PUBLIC_JSON_V1)
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginCommand command) {
        var token = userService.createToken(command).getTokenValue();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new LoginResponse(token));
    }
}
