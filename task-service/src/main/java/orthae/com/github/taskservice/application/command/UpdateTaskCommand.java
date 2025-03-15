package orthae.com.github.taskservice.application.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import orthae.com.github.taskservice.application.model.TaskStatusModel;
import orthae.com.github.taskservice.infrastructure.config.ValidEnum;

@Data
@AllArgsConstructor
public class UpdateTaskCommand {
    @NotBlank(message = "Title cannot be empty.")
    @Length(max = 100, message = "Title cannot be longer than 100 characters.")
    private final String title;

    @NotBlank(message = "Description cannot be empty.")
    @Length(max = 2500, message = "Description cannot be longer than 2500 characters.")
    private final String description;

    @ValidEnum(type = TaskStatusModel.class, message = "Status must use following values, 'PENDING', 'COMPLETED'.")
    private final String status;

    public TaskStatusModel getStatus() {
        return TaskStatusModel.valueOf(status);
    }
}