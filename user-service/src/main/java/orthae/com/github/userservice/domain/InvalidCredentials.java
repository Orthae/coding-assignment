package orthae.com.github.userservice.domain;

public class InvalidCredentials extends RuntimeException {
    public InvalidCredentials() {
        super("Entered credentials are invalid.");
    }
}
