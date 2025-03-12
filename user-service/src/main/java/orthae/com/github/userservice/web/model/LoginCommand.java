package orthae.com.github.userservice.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginCommand(
        @NotBlank(message = "username cannot be blank")
        @NotNull(message = "username is required")
        String username,

        @NotBlank(message = "password cannot be blank")
        @NotNull(message = "password is required")
        String password
) {}
