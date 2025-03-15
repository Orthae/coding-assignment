package orthae.com.github.userservice.infrastructure.config;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class EnumValidator implements ConstraintValidator<ValidEnum, String> {
    private ValidEnum annotation;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.stream(this.annotation.enumClass().getEnumConstants())
                .anyMatch(entry -> entry.name().equals(value));
    }
}
