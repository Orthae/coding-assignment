package orthae.com.github.userservice.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse{
    private final Instant timestamp;
    private final List<ErrorMessage> errors;
}