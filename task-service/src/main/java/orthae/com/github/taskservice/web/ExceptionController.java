package orthae.com.github.taskservice.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import orthae.com.github.taskservice.application.ActionUnauthorizedException;
import orthae.com.github.taskservice.application.InvalidTokenFormat;
import orthae.com.github.taskservice.application.TaskNotFoundException;

import java.time.Clock;
import java.util.List;

@ControllerAdvice
public class ExceptionController {
    private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

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

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(TaskNotFoundException exception) {
        var errors = List.of(ErrorMessage.ofMessage(exception.getMessage()));

        var response = ErrorResponse.builder()
                .errors(errors)
                .timestamp(clock.instant())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(ActionUnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handle(ActionUnauthorizedException exception) {
        var errors = List.of(ErrorMessage.ofMessage(exception.getMessage()));

        var response = ErrorResponse.builder()
                .errors(errors)
                .timestamp(clock.instant())
                .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(response);
    }

    @ExceptionHandler(InvalidTokenFormat.class)
    public ResponseEntity<Void> handle() {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .build();
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ErrorResponse> handle(ObjectOptimisticLockingFailureException exception) {
        log.error("Optimistic lock failed.", exception);

        var errors = List.of(ErrorMessage.ofMessage("Resource has been modified by other request."));

        var response = ErrorResponse.builder()
                .errors(errors)
                .timestamp(clock.instant())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handle(HttpMessageNotReadableException exception) {
        log.warn(exception.getMessage());

        var errors = List.of(ErrorMessage.ofMessage("Request body is in invalid format."));

        var response = ErrorResponse.builder()
                .errors(errors)
                .timestamp(clock.instant())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
