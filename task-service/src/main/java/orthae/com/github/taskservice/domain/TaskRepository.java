package orthae.com.github.taskservice.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository {
    Optional<Task> findById(UUID id);
    List<Task> findAllByUserId(UUID userId);
    List<Task> findAll();
    void save(Task task);
    void deleteById(UUID id);
}
