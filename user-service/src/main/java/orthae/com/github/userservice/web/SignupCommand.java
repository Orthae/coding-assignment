package orthae.com.github.userservice.web;


public record SignupCommand(String username, String password, RoleDto role) {
}
