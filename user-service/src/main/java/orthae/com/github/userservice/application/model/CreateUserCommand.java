package orthae.com.github.userservice.application.model;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import orthae.com.github.userservice.infrastructure.config.ValidEnum;

@Data
@AllArgsConstructor
public class CreateUserCommand {
        @NotBlank(message = "Username cannot be empty.")
        @Length(max = 50, message = "Username cannot be longer than 50 characters.")
        private final String username;

        @NotBlank(message = "Password cannot be empty.")
        private final String password;

        @ValidEnum(enumClass = RoleModel.class, message = "Role must use following values, 'ADMIN', 'USER'.")
        private final String role;

        public RoleModel getRole() {
            return RoleModel.valueOf(role);
        }
}
