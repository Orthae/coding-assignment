package orthae.com.github.taskservice.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorMessage(
        String field,
        String message
) {
    public static ErrorMessage from(ObjectError error) {
        String field = switch (error) {
            case FieldError fieldError -> fieldError.getField();
            default -> null;
        };

        String message = error.getDefaultMessage();

        return new ErrorMessage(field, message);
    }

    public static ErrorMessage ofMessage(String message) {
        return new ErrorMessage(null, message);
    }
}
