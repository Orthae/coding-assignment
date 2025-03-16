package orthae.com.github.taskservice;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseIntegrationTests {

    @LocalServerPort
    private int port;

    public String getUrl(String path) {
        return "http://localhost:" + port + "/" + path;
    }
}
