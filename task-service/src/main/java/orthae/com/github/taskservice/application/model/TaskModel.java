package orthae.com.github.taskservice.application.model;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class TaskModel {
    private final UUID id;
    private final UserModel createdBy;
    private final String title;
    private final String description;
    private final TaskStatusModel status;
}
