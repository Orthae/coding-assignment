package orthae.com.github.taskservice.domain;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @GetMapping
    public void x(JwtAuthenticationToken x) {
        System.out.println(x.getAuthorities());
//        x.getPrincipal();
    }
}
