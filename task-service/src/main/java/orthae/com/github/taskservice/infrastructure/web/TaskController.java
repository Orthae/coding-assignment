package orthae.com.github.taskservice.infrastructure.web;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import orthae.com.github.taskservice.domain.AuthenticatedUser;
import orthae.com.github.taskservice.domain.CreateTaskCommand;
import orthae.com.github.taskservice.domain.UpdateTaskCommand;

import java.util.UUID;

@RestController
public class TaskController {

    @PostMapping
    public void createTask(@RequestBody CreateTaskCommand command, JwtAuthenticationToken token)  {
        var user = AuthenticatedUser.from(token);

    }

    @GetMapping
    public void getTasks(JwtAuthenticationToken token)  {
        var user = AuthenticatedUser.from(token);
        System.out.println("user: " + user);
    }

    @PutMapping("{id}")
    public void updateTask(@PathVariable UUID id, @RequestBody UpdateTaskCommand command, JwtAuthenticationToken token)  {
        var user = AuthenticatedUser.from(token);

    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable UUID id, JwtAuthenticationToken token)  {
        var user = AuthenticatedUser.from(token);

    }
}
