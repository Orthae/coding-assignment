package orthae.com.github.userservice.domain;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists() {
        super("User already exists.");
    }
}
