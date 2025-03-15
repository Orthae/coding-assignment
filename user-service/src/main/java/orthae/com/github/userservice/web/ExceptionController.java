package orthae.com.github.userservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import orthae.com.github.userservice.application.InvalidCredentials;
import orthae.com.github.userservice.application.UserAlreadyExists;

import java.util.List;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exception) {
        List<ErrorMessage> errors = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ErrorMessage::from)
                .toList();

        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<ErrorResponse> handle(InvalidCredentials exception) {
        List<ErrorMessage> errors = List.of(ErrorMessage.ofMessage(exception.getMessage()));

        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<ErrorResponse> handle(UserAlreadyExists exception) {
        List<ErrorMessage> errors = List.of(ErrorMessage.ofMessage(exception.getMessage()));

        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }
}
