package orthae.com.github.userservice.application;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("User already exists.");
    }
}
