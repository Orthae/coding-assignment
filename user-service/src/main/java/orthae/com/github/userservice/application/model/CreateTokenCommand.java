package orthae.com.github.userservice.application.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class CreateTokenCommand{
        @NotBlank(message = "Username cannot be empty.")
        @Length(max = 64, message = "Username cannot be longer than 64 characters.")
        private final String username;

        @NotBlank(message = "Password cannot be empty.")
        private final String password;
}
