package orthae.com.github.taskservice.infrastructure.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

@Component
public class ResourceServerConfigurer {
    public void configure(OAuth2ResourceServerConfigurer<HttpSecurity> configurer) {
        configurer.jwt(this::customizeToken);
    }

    private void customizeToken(OAuth2ResourceServerConfigurer<HttpSecurity>.JwtConfigurer configurer) {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        grantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);

        configurer.jwtAuthenticationConverter(converter);
    }
}