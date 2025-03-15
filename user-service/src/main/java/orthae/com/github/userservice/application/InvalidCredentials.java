package orthae.com.github.userservice.application;

public class InvalidCredentials extends RuntimeException {
    public InvalidCredentials() {
        super("Entered credentials are invalid.");
    }
}
