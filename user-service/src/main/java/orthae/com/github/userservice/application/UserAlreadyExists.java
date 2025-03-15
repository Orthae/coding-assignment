package orthae.com.github.userservice.application;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists() {
        super("User already exists.");
    }
}
