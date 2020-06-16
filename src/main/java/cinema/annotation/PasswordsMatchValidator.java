package cinema.annotation;

import cinema.model.dto.UserRegistrationDto;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator
        implements ConstraintValidator<PasswordsMatch, UserRegistrationDto> {

    public boolean isValid(UserRegistrationDto userRegistrationDto,
                           ConstraintValidatorContext context) {
        return Objects.equals(userRegistrationDto.getPassword(),
                userRegistrationDto.getRepeatPassword());
    }
}
