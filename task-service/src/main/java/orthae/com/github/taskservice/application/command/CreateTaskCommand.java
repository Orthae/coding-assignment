package orthae.com.github.taskservice.application.command;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class CreateTaskCommand{
    @NotBlank(message = "Title cannot be empty")
    @Length(max = 100, message = "Title cannot be longer than 100 characters.")
    private final String title;

    @NotBlank(message = "Description cannot be empty")
    @Length(max = 2500, message = "Description cannot be longer than 2500 characters.")
    private final String description;
}
