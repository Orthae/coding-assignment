package orthae.com.github.taskservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import orthae.com.github.taskservice.application.ActionUnauthorized;
import orthae.com.github.taskservice.application.TaskNotFound;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.List;

@ControllerAdvice
public class ExceptionController {
    private final Clock clock;

    public ExceptionController(Clock clock) {
        this.clock = clock;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exception) {
        var errors = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ErrorMessage::from)
                .toList();

        var response = ErrorResponse.builder()
                .errors(errors)
                .timestamp(clock.instant())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskNotFound.class)
    public ResponseEntity<ErrorResponse> handle(TaskNotFound exception) {
        var errors = List.of(ErrorMessage.ofMessage(exception.getMessage()));

        var response = ErrorResponse.builder()
                .errors(errors)
                .timestamp(clock.instant())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ActionUnauthorized.class)
    public ResponseEntity<ErrorResponse> handle(ActionUnauthorized exception) {
        var errors = List.of(ErrorMessage.ofMessage(exception.getMessage()));

        var response = ErrorResponse.builder()
                .errors(errors)
                .timestamp(clock.instant())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
