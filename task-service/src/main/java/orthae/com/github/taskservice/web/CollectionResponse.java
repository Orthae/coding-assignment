package orthae.com.github.taskservice.web;

import java.util.Collection;

public record CollectionResponse<T>(Collection<T> items) {
    public static <T> CollectionResponse<T> of(Collection<T> items) {
        return new CollectionResponse<>(items);
    }
}
