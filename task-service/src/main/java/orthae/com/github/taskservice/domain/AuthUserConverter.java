package orthae.com.github.taskservice.domain;

import org.springframework.core.MethodParameter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AuthUserConverter implements Converter<JwtAuthenticationToken, AuthenticatedUser> {

    @Override
    public AuthenticatedUser convert(JwtAuthenticationToken source) {
        var x = source.getToken();
        return null;
    }
}
