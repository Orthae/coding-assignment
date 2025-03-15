package orthae.com.github.taskservice.application.model;

import orthae.com.github.taskservice.application.AuthenticatedUser;
import orthae.com.github.taskservice.application.command.CreateTaskCommand;
import orthae.com.github.taskservice.application.command.UpdateTaskCommand;
import orthae.com.github.taskservice.domain.*;

import java.util.UUID;

public class TaskMapper {

    public static Task createTask(CreateTaskCommand command, AuthenticatedUser user) {
        return Task.builder()
                .id(UUID.randomUUID())
                .createdBy(UserMapper.toDomain(user))
                .title(command.getTitle())
                .description(command.getDescription())
                .status(TaskStatus.PENDING)
                .build();
    }

    public static Task updateTask(UpdateTaskCommand command, Task task) {
        return Task.builder()
                .id(task.getId())
                .createdBy(task.getCreatedBy())
                .title(command.getTitle())
                .description(command.getDescription())
                .status(StatusMapper.toDomain(command.getStatus()))
                .version(task.getVersion())
                .build();
    }

    public static TaskModel toModel(Task task) {
        return TaskModel.builder()
                .id(task.getId())
                .createdBy(UserMapper.toModel(task.getCreatedBy()))
                .title(task.getTitle())
                .description(task.getDescription())
                .status(StatusMapper.toModel(task.getStatus()))
                .build();
    }

    private static class UserMapper {
        public static UserModel toModel(User user) {
            return new UserModel(user.getId());
        }

        public static User toDomain(AuthenticatedUser model) {
            return new User(model.getId());
        }
    }

    private static class StatusMapper {
        public static TaskStatusModel toModel(TaskStatus status) {
            return switch (status) {
                case PENDING -> TaskStatusModel.PENDING;
                case COMPLETED -> TaskStatusModel.COMPLETED;
            };
        }

        public static TaskStatus toDomain(TaskStatusModel model){
            return switch (model) {
                case PENDING -> TaskStatus.PENDING;
                case COMPLETED -> TaskStatus.COMPLETED;
            };
        }
    }
}
