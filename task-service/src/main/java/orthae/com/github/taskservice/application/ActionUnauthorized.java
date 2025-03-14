package orthae.com.github.taskservice.application;

public class ActionUnauthorized extends RuntimeException {
    public ActionUnauthorized() {
        super("You don't have permission to perform this action.");
    }
}
