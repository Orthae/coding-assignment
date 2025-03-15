package orthae.com.github.userservice.application;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Entered credentials are invalid.");
    }
}
