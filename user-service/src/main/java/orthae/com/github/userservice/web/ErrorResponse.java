package orthae.com.github.userservice.web;

import java.util.List;

public record ErrorResponse(
        List<ErrorMessage> errors
) {
}