package orthae.com.github.taskservice.application;

public class ActionUnauthorizedException extends RuntimeException {
    public ActionUnauthorizedException() {
        super("You don't have permission to perform this action.");
    }
}
