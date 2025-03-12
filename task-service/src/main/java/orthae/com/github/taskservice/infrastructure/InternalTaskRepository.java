package orthae.com.github.taskservice.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import orthae.com.github.taskservice.domain.Task;

import java.util.List;
import java.util.UUID;

interface InternalTaskRepository extends JpaRepository<TaskEntity, UUID> {
    List<TaskEntity> findAllByUserId(UUID userId);
}
