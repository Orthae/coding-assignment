package orthae.com.github.taskservice.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private final List<ErrorMessage> errors;
    private final Instant timestamp;
}
