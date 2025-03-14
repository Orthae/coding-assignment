package orthae.com.github.taskservice.application;

import org.springframework.stereotype.Service;
import orthae.com.github.taskservice.application.command.CreateTaskCommand;
import orthae.com.github.taskservice.application.command.UpdateTaskCommand;
import orthae.com.github.taskservice.application.model.TaskModel;
import orthae.com.github.taskservice.domain.*;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskModel createTask(CreateTaskCommand command, AuthenticatedUser user) {
        var task = TaskMapper.createTask(command, user);
        repository.save(task);

        return TaskMapper.toModel(task);
    }

    public List<TaskModel> getTasks(AuthenticatedUser user) {
        var tasks = switch (user.role()) {
            case ADMIN -> repository.findAll();
            case USER -> repository.findAllByUserId(user.id());
        };

        return tasks.stream()
                .map(TaskMapper::toModel)
                .toList();
    }

    public TaskModel updateTask(UUID id, UpdateTaskCommand command, AuthenticatedUser user) {
        var task = repository.findById(id).orElseThrow(TaskNotFound::new);
        TaskAuthorizer.authorize(task, user);

        var updatedTask = TaskMapper.updateTask(command, task);
        repository.save(updatedTask);

        return TaskMapper.toModel(updatedTask);
    }

    public TaskModel deleteTask(UUID id, AuthenticatedUser user) {
        var task = repository.findById(id).orElseThrow(TaskNotFound::new);
        TaskAuthorizer.authorize(task, user);

        repository.deleteById(task.getId());
        return TaskMapper.toModel(task);
    }
}
