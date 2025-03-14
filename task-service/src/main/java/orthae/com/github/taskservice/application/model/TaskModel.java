package orthae.com.github.taskservice.application.model;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskModel {
    private UUID id;
    private UserModel createdBy;
    private String title;
    private String description;
    private TaskStatusModel status;
}



