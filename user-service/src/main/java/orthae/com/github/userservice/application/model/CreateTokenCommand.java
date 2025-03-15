package orthae.com.github.userservice.application.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateTokenCommand{
        @NotBlank(message = "Username cannot be empty.")
        private final String username;

        @NotBlank(message = "Password cannot be empty.")
        private final String password;
}
