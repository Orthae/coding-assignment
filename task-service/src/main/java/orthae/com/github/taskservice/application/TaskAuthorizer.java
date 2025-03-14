package orthae.com.github.taskservice.application;

import orthae.com.github.taskservice.domain.Task;

public class TaskAuthorizer {
    public static void authorize(Task task, AuthenticatedUser user) {
        if(user.role().equals(Role.ADMIN)) {
            return;
        }

        if(task.getCreatedBy().getId().equals(user.id())) {
            return;
        }

        throw new ActionUnauthorized();
    }
}
