package hex.model.users.custom_validator;

import hex.repository.UserRepositoryEnt;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<EmailUnique, String> {

    @Autowired
    private UserRepositoryEnt userRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.selectUserByEmail(s).isEmpty();
    }

    @Override
    public void initialize(EmailUnique constraintAnnotation) {
    }
}
