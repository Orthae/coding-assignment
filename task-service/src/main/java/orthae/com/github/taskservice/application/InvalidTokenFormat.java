package orthae.com.github.taskservice.application;

public class InvalidTokenFormat extends RuntimeException {
    public InvalidTokenFormat(String message, Throwable cause) {
        super(message, cause);
    }
}
