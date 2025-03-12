package orthae.com.github.taskservice.infrastructure;

import org.springframework.stereotype.Repository;
import orthae.com.github.taskservice.domain.Task;
import orthae.com.github.taskservice.domain.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
class TaskRepositoryImpl implements TaskRepository {
    private final InternalTaskRepository repository;

    public TaskRepositoryImpl(InternalTaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Task> findAllByUserId(UUID userId) {
        return repository.findAll;
    }

    @Override
    public List<Task> findAll() {
        return List.of();
    }

    @Override
    public void save(Task task) {

    }

    @Override
    public void deleteById(UUID id) {

    }
}
