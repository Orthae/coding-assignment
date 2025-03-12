package orthae.com.github.taskservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

//title, description, status (PENDING, COMPLETED), createdBy (User)

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Task {
    private UUID id;
    private User createdBy;
    private String title;
    private String description;
    private TaskStatus status;
}
