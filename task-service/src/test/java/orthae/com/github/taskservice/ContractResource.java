package orthae.com.github.taskservice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ContractResource {
    public static String response(String name) throws IOException {
        return resource("response", name);
    }

    public static String request(String name) throws IOException {
        return resource("request", name);
    }

    private static String resource(String type, String name) throws IOException {
        return Files.readString(Path.of("src", "test", "resources", "contract", type, name + ".json"));
    }
}
