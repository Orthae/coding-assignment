package orthae.com.github.taskservice.infrastructure.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationRequestMatcherConfigurer {
    private static final String[] WHITELIST = {
            "/actuator/prometheus",
            "/actuator/health",
            "/actuator/health/livenessState",
            "/actuator/health/readinessState"
    };

    public void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry configurer) {
        configurer
                .requestMatchers(WHITELIST).permitAll()
                .anyRequest().authenticated();
    }
}
