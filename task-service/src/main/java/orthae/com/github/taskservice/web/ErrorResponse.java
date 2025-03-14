package orthae.com.github.taskservice.web;

import java.util.List;

public record ErrorResponse(
        List<ErrorMessage> errors
) {
}