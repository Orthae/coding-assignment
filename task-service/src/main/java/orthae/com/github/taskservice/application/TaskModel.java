package orthae.com.github.taskservice.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import orthae.com.github.taskservice.domain.TaskStatus;
import orthae.com.github.taskservice.domain.User;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskModel {
    private UUID id;
    private User createdBy;
    private String title;
    private String description;
    private TaskStatus status;
}
