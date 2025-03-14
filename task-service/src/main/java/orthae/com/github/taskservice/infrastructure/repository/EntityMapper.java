package orthae.com.github.taskservice.infrastructure.repository;

import orthae.com.github.taskservice.domain.Task;
import orthae.com.github.taskservice.domain.TaskStatus;
import orthae.com.github.taskservice.domain.User;

public class EntityMapper {

    public static Task toDomain(TaskEntity entity) {
        return Task.builder()
                .id(entity.getId())
                .createdBy(new User(entity.getUserId()))
                .title(entity.getTitle())
                .description(entity.getDescription())
                .status(StatusMapper.toDomain(entity.getStatus()))
                .version(entity.getVersion())
                .build();
    }

    public static TaskEntity toEntity(Task task) {
        return TaskEntity.builder()
                .id(task.getId())
                .userId(task.getCreatedBy().getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(StatusMapper.toEntity(task.getStatus()))
                .version(task.getVersion())
                .build();
    }

    private static class StatusMapper {
        public static TaskStatus toDomain(TaskStatusEntity entity) {
            return switch (entity) {
                case PENDING -> TaskStatus.PENDING;
                case COMPLETED -> TaskStatus.COMPLETED;
            };
        }

        public static TaskStatusEntity toEntity(TaskStatus status) {
            return switch (status) {
                case PENDING -> TaskStatusEntity.PENDING;
                case COMPLETED -> TaskStatusEntity.COMPLETED;
            };
        }
    }
}
