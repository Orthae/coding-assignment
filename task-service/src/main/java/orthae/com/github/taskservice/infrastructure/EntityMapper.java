package orthae.com.github.taskservice.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import orthae.com.github.taskservice.domain.Task;
import orthae.com.github.taskservice.domain.TaskStatus;
import orthae.com.github.taskservice.domain.User;

import java.util.UUID;

public class EntityMapper {

    public static Task toDomain(TaskEntity entity) {
        return new Task(
                entity.getId(),
                new User(entity.getUserId()),
                entity.getTitle(),
                entity.getDescription(),
                switch (entity.getStatus()) {
                    case PENDING -> TaskStatus.PENDING;
                    case COMPLETED -> TaskStatus.COMPLETED;
                }
        );
    }

    public static TaskEntity toEntity(Task task) {
        return new TaskEntity(
                task.getId(),
                task.getCreatedBy().getId(),
                task.getTitle(),
                task.getDescription(),
                switch (task.getStatus()) {
                    case PENDING -> TaskStatusEntity.PENDING;
                    case COMPLETED -> TaskStatusEntity.COMPLETED;
                }
        );
    }
}
