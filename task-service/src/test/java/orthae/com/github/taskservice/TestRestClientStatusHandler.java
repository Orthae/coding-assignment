package orthae.com.github.taskservice;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class TestRestClientStatusHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(@NotNull ClientHttpResponse response) {
        return true;
    }

    @Override
    public void handleError(@NotNull ClientHttpResponse response) {

    }
}
