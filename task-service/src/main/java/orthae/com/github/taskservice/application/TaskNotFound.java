package orthae.com.github.taskservice.application;

public class TaskNotFound extends RuntimeException {
    public TaskNotFound() {
        super("Task not found.");
    }
}
