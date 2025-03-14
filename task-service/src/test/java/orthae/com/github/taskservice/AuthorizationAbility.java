package orthae.com.github.taskservice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class AuthorizationAbility {
    public static void stubAuthorizationServer() throws IOException {
        var responseBody = Files.readString(Path.of("src", "test", "resources", "wiremock", "jwks.json"));
        stubFor(get("/jwks.json").willReturn(aResponse().withBody(responseBody)));
    }
}
