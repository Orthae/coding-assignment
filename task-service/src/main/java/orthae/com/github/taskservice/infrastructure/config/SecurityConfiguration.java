package orthae.com.github.taskservice.infrastructure.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final AuthorizationRequestMatcherConfigurer authorizationRequestMatcherConfigurer;
    private final ResourceServerConfigurer resourceServerConfigurer;

    public SecurityConfiguration(AuthorizationRequestMatcherConfigurer authorizationRequestMatcherConfigurer, ResourceServerConfigurer resourceServerConfigurer) {
        this.authorizationRequestMatcherConfigurer = authorizationRequestMatcherConfigurer;
        this.resourceServerConfigurer = resourceServerConfigurer;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizationRequestMatcherConfigurer::configure)
                .oauth2ResourceServer(resourceServerConfigurer::configure)
                .build();

    }

    @Bean
    public JwtDecoder jwtDecoder(OAuth2ResourceServerProperties properties) {
        return Optional.ofNullable(properties.getJwt().getJwkSetUri())
                .map(uri -> NimbusJwtDecoder.withJwkSetUri(uri).build())
                .orElseThrow(() -> new IllegalArgumentException("JwkSetUri is required."));
    }
}
