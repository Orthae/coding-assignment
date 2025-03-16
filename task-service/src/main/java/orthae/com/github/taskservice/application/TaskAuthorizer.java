package orthae.com.github.taskservice.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import orthae.com.github.taskservice.domain.Task;

public class TaskAuthorizer {
    private static final Logger log = LoggerFactory.getLogger(TaskAuthorizer.class);

    public static void authorize(Task task, AuthenticatedUser user) {
        if(user.getRole().equals(Role.ADMIN)) {
            return;
        }

        if(task.getCreatedBy().getId().equals(user.getId())) {
            return;
        }

        log.info("User {} is not authorized to perform action on task {}.", user.getId(), task.getId());
        throw new ActionUnauthorizedException();
    }
}
