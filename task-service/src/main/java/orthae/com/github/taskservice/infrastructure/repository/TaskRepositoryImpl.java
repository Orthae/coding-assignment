package orthae.com.github.taskservice.infrastructure.repository;

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
        return repository.findById(id)
                .map(EntityMapper::toDomain);
    }

    @Override
    public List<Task> findAllByUserId(UUID userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(EntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Task> findAll() {
        return repository.findAll()
                .stream()
                .map(EntityMapper::toDomain)
                .toList();
    }

    @Override
    public void save(Task task) {
        repository.save(EntityMapper.toEntity(task));
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
