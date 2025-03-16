package orthae.com.github.taskservice;


import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;
import orthae.com.github.taskservice.application.TaskService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static orthae.com.github.taskservice.TestFixtures.*;

@WireMockTest(httpPort = 9080)
public class TaskControllerContractTest extends BaseIntegrationTests {


    private final RestClient client = RestClient.builder()
            .build();

    @MockBean
    private TaskService taskService;

    @Test
    void shouldReturnCorrectResponseForGet() throws IOException {
        AuthorizationAbility.stubAuthorizationServer();
        when(taskService.getTasks(any())).thenReturn(List.of(TestFixtures.TASK_MODEL));

        var response = client.get()
                .uri(getUrl(BASE_TASK_URL))
                .header("Authorization", TestFixtures.ALIVE_ADMIN_TOKEN)
                .header("Accept", MEDIA_TYPE)
                .retrieve()
                .toEntity(String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ContractResource.response("get_tasks"), response.getBody());
    }

    @Test
    void shouldReturnCorrectResponseForPost() throws IOException {
        AuthorizationAbility.stubAuthorizationServer();
        when(taskService.createTask(any(), any())).thenReturn(TestFixtures.TASK_MODEL);

        var response = client.post()
                .uri(getUrl(BASE_TASK_URL))
                .header("Authorization", TestFixtures.ALIVE_ADMIN_TOKEN)
                .header("Accept", MEDIA_TYPE)
                .header("Content-Type", MEDIA_TYPE)
                .body(ContractResource.request("create_task"))
                .retrieve()
                .toEntity(String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ContractResource.response("create_task"), response.getBody());
    }

    @Test
    void shouldReturnCorrectResponseForPut() throws IOException {
        AuthorizationAbility.stubAuthorizationServer();
        when(taskService.updateTask(any(), any(), any())).thenReturn(TestFixtures.TASK_MODEL);

        var response = client.put()
                .uri(getUrl(ID_TASK_URL).formatted(TestFixtures.TASK_MODEL.getId()))
                .header("Authorization", TestFixtures.ALIVE_ADMIN_TOKEN)
                .header("Accept", MEDIA_TYPE)
                .header("Content-Type", MEDIA_TYPE)
                .body(ContractResource.request("update_task"))
                .retrieve()
                .toEntity(String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ContractResource.response("update_task"), response.getBody());
    }

    @Test
    void shouldReturnCorrectResponseForDelete() throws IOException {
        AuthorizationAbility.stubAuthorizationServer();
        when(taskService.deleteTask(any(), any())).thenReturn(TestFixtures.TASK_MODEL);

        var response = client.delete()
                .uri(getUrl(ID_TASK_URL).formatted(TestFixtures.TASK_MODEL.getId()))
                .header("Authorization", TestFixtures.ALIVE_ADMIN_TOKEN)
                .header("Accept", MEDIA_TYPE)
                .retrieve()
                .toEntity(String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ContractResource.response("delete_task"), response.getBody());
    }
}
