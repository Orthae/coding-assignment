package orthae.com.github.taskservice;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TaskControllerTests extends BaseIntegrationTests {

    public RestClient client = RestClient.builder().build();

    @Test
    void test() {
        client.get()
                .uri("http://localhost:8080")
                .retrieve();
    }
}
