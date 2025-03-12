package orthae.com.github.userservice.web.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignupCommand(
        @NotBlank
        @NotNull
        String username,

        @NotBlank
        @NotNull
        String password,

        @NotNull
        RoleDto role
) {
}
