package orthae.com.github.taskservice.application;

import orthae.com.github.taskservice.domain.Task;

public class TaskAuthorizer {
    public static void authorize(Task task, AuthenticatedUser user) {
        if(user.getRole().equals(Role.ADMIN)) {
            return;
        }

        if(task.getCreatedBy().getId().equals(user.getId())) {
            return;
        }

        throw new ActionUnauthorizedException();
    }
}
