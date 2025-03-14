package orthae.com.github.taskservice;


import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;
import orthae.com.github.taskservice.application.TaskService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@WireMockTest(httpPort = 9080)
public class TaskControllerTests extends BaseIntegrationTests {
    private static final String BASE_TASK_URL = "http://localhost:8080/tasks";
    private static final String ID_TASK_URL = "http://localhost:8080/tasks/{}";

    @MockBean
    private TaskService service;

    public RestClient client = RestClient.builder()
            .defaultStatusHandler(new TestRestClientStatusHandler())
            .build();

    @Test
    void shouldReturnUnauthorizedForExpiredToken() throws IOException {
        AuthorizationAbility.stubAuthorizationServer();
        Mockito.when(service.getTasks(TokenFixtures.AUTHENTICATED_ADMIN)).thenReturn(List.of());

        var response = client.get()
                .uri(BASE_TASK_URL)
                .header("Authorization", TokenFixtures.ALIVE_ADMIN_TOKEN)
                .retrieve()
                .toEntity(List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(response.getBody(), List.of());
    }

    @Test
    void shouldReturnUnauthorizedForExpiredToken2() throws IOException {
        AuthorizationAbility.stubAuthorizationServer();

        var response = client.post()
                .uri(BASE_TASK_URL)
                .header("Authorization", TokenFixtures.EXPIRED_TOKEN)
                .retrieve()
                .toBodilessEntity();

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void shouldReturnUnauthorizedForExpiredToken3() throws IOException {
        AuthorizationAbility.stubAuthorizationServer();

        var response = client.put()
                .uri(ID_TASK_URL, UUID.randomUUID())
                .header("Authorization", TokenFixtures.EXPIRED_TOKEN)
                .retrieve()
                .toBodilessEntity();

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void shouldReturnUnauthorizedForExpiredToken4() throws IOException {
        AuthorizationAbility.stubAuthorizationServer();

        var response = client.delete()
                .uri(ID_TASK_URL, UUID.randomUUID())
                .header("Authorization", TokenFixtures.EXPIRED_TOKEN)
                .retrieve()
                .toBodilessEntity();

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}
