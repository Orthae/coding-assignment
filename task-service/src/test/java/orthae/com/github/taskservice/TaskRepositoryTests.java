package orthae.com.github.taskservice;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import orthae.com.github.taskservice.domain.Task;
import orthae.com.github.taskservice.domain.TaskRepository;
import orthae.com.github.taskservice.domain.TaskStatus;
import orthae.com.github.taskservice.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
public class TaskRepositoryTests extends BaseIntegrationTests {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    @Autowired
    TaskRepository repository;

    @Test
    void shouldSaveAndFindTaskById() {
        UUID taskId = UUID.randomUUID();
        Task task = Task.builder()
                .id(taskId)
                .createdBy(new User(UUID.randomUUID()))
                .title("Sample")
                .description("Test")
                .status(TaskStatus.PENDING)
                .build();

        repository.save(task);
        Task found = repository.findById(taskId).orElseThrow();

        assertEquals(task, found);
    }

    @Test
    void shouldFindAllTasksByUserId() {
        UUID userId = UUID.randomUUID();
        Task task1 = Task.builder()
                .id(UUID.randomUUID())
                .createdBy(new User(userId))
                .title("Task 1")
                .description("Test")
                .status(TaskStatus.PENDING)
                .build();

        Task task2 = Task.builder()
                .id(UUID.randomUUID())
                .createdBy(new User(userId))
                .title("Task 2")
                .description("Test")
                .status(TaskStatus.PENDING)
                .build();

        UUID otherUserId = UUID.randomUUID();
        Task task3 = Task.builder()
                .id(UUID.randomUUID())
                .createdBy(new User(otherUserId))
                .title("Task 3")
                .description("Test")
                .status(TaskStatus.PENDING)
                .build();

        repository.save(task1);
        repository.save(task2);
        repository.save(task3);

        List<Task> userTasks = repository.findAllByUserId(userId);
        assertEquals(2, userTasks.size());
        assertEquals(task1, userTasks.getFirst());
        assertEquals(task2, userTasks.getLast());

        List<Task> otherUserTasks = repository.findAllByUserId(otherUserId);
        assertEquals(1, otherUserTasks.size());
        assertEquals(task3, otherUserTasks.getFirst());
    }

    @Test
    void shouldDeleteTaskById() {
        UUID taskId = UUID.randomUUID();
        Task task = Task.builder()
                .id(taskId)
                .createdBy(new User(UUID.randomUUID()))
                .title("Delete")
                .description("Test")
                .status(TaskStatus.PENDING)
                .build();

        repository.save(task);
        repository.deleteById(taskId);

        Optional<Task> found = repository.findById(taskId);
        assertTrue(found.isEmpty());
    }

    @Test
    void shouldIncrementVersionOnUpdate() {
        UUID taskId = UUID.randomUUID();
        Task task = Task.builder()
                .id(taskId)
                .createdBy(new User(UUID.randomUUID()))
                .title("Version Test")
                .description("Test")
                .status(TaskStatus.PENDING)
                .build();

        repository.save(task);
        Task savedTask = repository.findById(taskId).orElseThrow();

        Task updatedTask = Task.builder()
                .id(taskId)
                .createdBy(savedTask.getCreatedBy())
                .title(savedTask.getTitle())
                .description(savedTask.getDescription())
                .status(TaskStatus.COMPLETED)
                .version(savedTask.getVersion())
                .build();

        repository.save(updatedTask);

        Task savedUpdatedTask = repository.findById(taskId).orElseThrow();

        assertEquals(savedTask.getVersion() + 1, savedUpdatedTask.getVersion());
    }

    @Test
    void shouldNotIncrementVersionIfNoChanges() {
        UUID taskId = UUID.randomUUID();
        Task task = Task.builder()
                .id(taskId)
                .createdBy(new User(UUID.randomUUID()))
                .title("Version Test")
                .description("Test")
                .status(TaskStatus.PENDING)
                .build();

        repository.save(task);
        Task savedTask = repository.findById(taskId).orElseThrow();

        Task unchangedTask = Task.builder()
                .id(taskId)
                .createdBy(savedTask.getCreatedBy())
                .title(savedTask.getTitle())
                .description(savedTask.getDescription())
                .status(savedTask.getStatus())
                .version(savedTask.getVersion())
                .build();

        repository.save(unchangedTask);
        Task savedUnchagnedTask = repository.findById(taskId).orElseThrow();

        assertEquals(savedTask.getVersion(), savedUnchagnedTask.getVersion());
    }

    @Test
    void shouldThrowOnOptimisticLockConflict() {
        Task initialTask = Task.builder()
                .id(UUID.randomUUID())
                .createdBy(new User(UUID.randomUUID()))
                .title("Conflict Test")
                .description("Test")
                .status(TaskStatus.PENDING)
                .build();

        repository.save(initialTask);

        Task firstLoad = repository.findById(initialTask.getId()).orElseThrow();
        Task updatedFirst = Task.builder()
                .id(firstLoad.getId())
                .createdBy(firstLoad.getCreatedBy())
                .title("First Update")
                .description(firstLoad.getDescription())
                .status(firstLoad.getStatus())
                .version(firstLoad.getVersion())
                .build();

        Task secondLoad = repository.findById(initialTask.getId()).orElseThrow();
        Task updatedSecond = Task.builder()
                .id(secondLoad.getId())
                .createdBy(secondLoad.getCreatedBy())
                .title("Second Update")
                .description(secondLoad.getDescription())
                .status(secondLoad.getStatus())
                .version(secondLoad.getVersion())
                .build();

        assertDoesNotThrow(() -> repository.save(updatedFirst));
        assertThrows(ObjectOptimisticLockingFailureException.class, () -> repository.save(updatedSecond));
    }
}
