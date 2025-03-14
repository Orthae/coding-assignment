package orthae.com.github.taskservice.application;

import orthae.com.github.taskservice.domain.*;

import java.util.UUID;

public class TaskMapper {

    public static Task createTask(CreateTaskCommand command, AuthenticatedUser user) {
        return Task.builder()
                .id(UUID.randomUUID())
                .createdBy(new User(user.id()))
                .title(command.title())
                .description(command.description())
                .status(TaskStatus.PENDING)
                .build();
    }

    public static Task updateTask(UpdateTaskCommand command, Task task) {
        return Task.builder()
                .id(task.getId())
                .createdBy(task.getCreatedBy())
                .title(command.title())
                .description(command.description())
                .status(command.status())
                .version(task.getVersion())
                .build();
    }

    public static TaskModel toModel(Task task) {
        return TaskModel.builder()
                .id(task.getId())
                .createdBy(task.getCreatedBy())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .build();
    }
}
