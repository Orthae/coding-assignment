package orthae.com.github.taskservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import orthae.com.github.taskservice.application.TaskModel;
import orthae.com.github.taskservice.application.TaskService;
import orthae.com.github.taskservice.domain.AuthenticatedUser;
import orthae.com.github.taskservice.domain.CreateTaskCommand;
import orthae.com.github.taskservice.domain.UpdateTaskCommand;

import java.util.List;
import java.util.UUID;

@RestController
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TaskModel> createTask(@RequestBody CreateTaskCommand command, JwtAuthenticationToken token)  {
        var user = AuthenticatedUser.from(token);
        var task = service.createTask(command, user);

        return ResponseEntity.status(HttpStatus.OK)
                .body(task);
    }

    @GetMapping
    public ResponseEntity<List<TaskModel>> getTasks(JwtAuthenticationToken token)  {
        var user = AuthenticatedUser.from(token);
        var tasks = service.getTasks(user);

        return ResponseEntity.status(HttpStatus.OK)
                .body(tasks);
    }

    @PutMapping("{id}")
    public ResponseEntity<TaskModel> updateTask(@PathVariable UUID id, @RequestBody UpdateTaskCommand command, JwtAuthenticationToken token)  {
        var user = AuthenticatedUser.from(token);
        var task = service.updateTask(id, command, user);

        return ResponseEntity.status(HttpStatus.OK)
                .body(task);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TaskModel> deleteTask(@PathVariable UUID id, JwtAuthenticationToken token)  {
        var user = AuthenticatedUser.from(token);
        var task = service.deleteTask(id, user);

        return ResponseEntity.status(HttpStatus.OK)
                .body(task);
    }
}
