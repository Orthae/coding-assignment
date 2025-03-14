package orthae.com.github.taskservice.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import orthae.com.github.taskservice.application.model.TaskModel;
import orthae.com.github.taskservice.application.TaskService;
import orthae.com.github.taskservice.application.AuthenticatedUser;
import orthae.com.github.taskservice.application.command.CreateTaskCommand;
import orthae.com.github.taskservice.application.command.UpdateTaskCommand;

import java.util.UUID;

@RestController
@RequestMapping("tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.PUBLIC_JSON_V1, produces = MediaType.PUBLIC_JSON_V1)
    public ResponseEntity<TaskModel> createTask(@Valid @RequestBody CreateTaskCommand command, JwtAuthenticationToken token)  {
        var user = AuthenticatedUser.from(token);
        var task = service.createTask(command, user);

        return ResponseEntity.status(HttpStatus.OK)
                .body(task);
    }

    @GetMapping(produces = MediaType.PUBLIC_JSON_V1)
    public ResponseEntity<CollectionResponse<TaskModel>> getTasks(JwtAuthenticationToken token)  {
        var user = AuthenticatedUser.from(token);
        var tasks = service.getTasks(user);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CollectionResponse.of(tasks));
    }

    @PutMapping(value = "{id}", produces = MediaType.PUBLIC_JSON_V1, consumes = MediaType.PUBLIC_JSON_V1)
    public ResponseEntity<TaskModel> updateTask(@PathVariable UUID id, @Valid @RequestBody UpdateTaskCommand command, JwtAuthenticationToken token)  {
        var user = AuthenticatedUser.from(token);
        var task = service.updateTask(id, command, user);

        return ResponseEntity.status(HttpStatus.OK)
                .body(task);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.PUBLIC_JSON_V1)
    public ResponseEntity<TaskModel> deleteTask(@PathVariable UUID id, JwtAuthenticationToken token)  {
        var user = AuthenticatedUser.from(token);
        var task = service.deleteTask(id, user);

        return ResponseEntity.status(HttpStatus.OK)
                .body(task);
    }
}
