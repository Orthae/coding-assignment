package orthae.com.github.taskservice.domain;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class User {
    private final UUID id;
}
