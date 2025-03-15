package orthae.com.github.userservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import orthae.com.github.userservice.application.InvalidCredentialsException;
import orthae.com.github.userservice.application.UserAlreadyExistsException;

import java.time.Clock;
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

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handle(InvalidCredentialsException exception) {
        var errors = List.of(ErrorMessage.ofMessage(exception.getMessage()));

        var response = ErrorResponse.builder()
                .errors(errors)
                .timestamp(clock.instant())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handle(UserAlreadyExistsException exception) {
        var errors = List.of(ErrorMessage.ofMessage(exception.getMessage()));

        var response = ErrorResponse.builder()
                .errors(errors)
                .timestamp(clock.instant())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
