package orthae.com.github.taskservice;


import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static orthae.com.github.taskservice.TestFixtures.BASE_TASK_URL;
import static orthae.com.github.taskservice.TestFixtures.ID_TASK_URL;

@WireMockTest(httpPort = 9080)
public class TaskControllerAuthorizationTests extends BaseIntegrationTests {
    public RestClient client = RestClient.builder()
            .defaultStatusHandler(new TestRestClientStatusHandler())
            .build();

    @Test
    void shouldReturnUnauthorizedForExpiredToken() throws IOException {
        AuthorizationAbility.stubAuthorizationServer();

        var response = client.get()
                .uri(getUrl(BASE_TASK_URL))
                .header("Authorization", TestFixtures.EXPIRED_TOKEN)
                .retrieve()
                .toBodilessEntity();

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void shouldReturnUnauthorizedForExpiredToken2() throws IOException {
        AuthorizationAbility.stubAuthorizationServer();

        var response = client.post()
                .uri(getUrl(BASE_TASK_URL))
                .header("Authorization", TestFixtures.EXPIRED_TOKEN)
                .retrieve()
                .toBodilessEntity();

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void shouldReturnUnauthorizedForExpiredToken3() throws IOException {
        AuthorizationAbility.stubAuthorizationServer();

        var response = client.put()
                .uri(getUrl(ID_TASK_URL).formatted(TestFixtures.TASK_MODEL.getId()))
                .header("Authorization", TestFixtures.EXPIRED_TOKEN)
                .retrieve()
                .toBodilessEntity();

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void shouldReturnUnauthorizedForExpiredToken4() throws IOException {
        AuthorizationAbility.stubAuthorizationServer();

        var response = client.delete()
                .uri(getUrl(ID_TASK_URL).formatted(TestFixtures.TASK_MODEL.getId()))
                .header("Authorization", TestFixtures.EXPIRED_TOKEN)
                .retrieve()
                .toBodilessEntity();

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}
