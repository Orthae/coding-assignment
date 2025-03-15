package orthae.com.github.userservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import orthae.com.github.userservice.application.InvalidCredentials;
import orthae.com.github.userservice.application.UserAlreadyExists;

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

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<ErrorResponse> handle(InvalidCredentials exception) {
        var errors = List.of(ErrorMessage.ofMessage(exception.getMessage()));

        var response = ErrorResponse.builder()
                .errors(errors)
                .timestamp(clock.instant())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<ErrorResponse> handle(UserAlreadyExists exception) {
        var errors = List.of(ErrorMessage.ofMessage(exception.getMessage()));

        var response = ErrorResponse.builder()
                .errors(errors)
                .timestamp(clock.instant())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
