package orthae.com.github.taskservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import orthae.com.github.taskservice.application.ActionUnauthorized;
import orthae.com.github.taskservice.application.TaskNotFound;

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

    @ExceptionHandler(TaskNotFound.class)
    public ResponseEntity<ErrorResponse> handle(TaskNotFound exception) {
        List<ErrorMessage> errors = List.of(ErrorMessage.ofMessage(exception.getMessage()));

        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ActionUnauthorized.class)
    public ResponseEntity<ErrorResponse> handle(ActionUnauthorized exception) {
        List<ErrorMessage> errors = List.of(ErrorMessage.ofMessage(exception.getMessage()));

        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.UNAUTHORIZED);
    }
}