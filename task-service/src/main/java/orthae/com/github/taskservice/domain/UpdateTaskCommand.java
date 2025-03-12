package orthae.com.github.taskservice.domain;

public record UpdateTaskCommand(
        String title,
        String description,
        TaskStatus status
) {
}
