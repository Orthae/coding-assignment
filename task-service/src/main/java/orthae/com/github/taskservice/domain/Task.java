package orthae.com.github.taskservice.domain;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
public class Task {
    private final UUID id;
    private final User createdBy;
    private final String title;
    private final String description;
    private final TaskStatus status;
    private final int version;
}
