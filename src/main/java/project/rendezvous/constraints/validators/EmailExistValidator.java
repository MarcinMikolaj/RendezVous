package project.rendezvous.constraints.validators;

import org.springframework.beans.factory.annotation.Autowired;
import project.rendezvous.constraints.EmailExist;
import project.rendezvous.registration.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailExistValidator implements ConstraintValidator<EmailExist, String> {

    private UserRepository userRepository;

    @Autowired
    public EmailExistValidator(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(EmailExist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(value != null && userRepository.existsByEmail(value) == false){
            return true;
        }else
            return false;

    }
}
