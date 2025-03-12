package orthae.com.github.taskservice.domain;

public record CreateTaskCommand(
        String title,
        String description
) {
}
