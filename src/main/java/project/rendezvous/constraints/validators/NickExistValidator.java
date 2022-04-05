package project.rendezvous.constraints.validators;

import org.springframework.beans.factory.annotation.Autowired;
import project.rendezvous.constraints.NickExist;
import project.rendezvous.registration.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NickExistValidator implements ConstraintValidator<NickExist, String> {

    private UserRepository userRepository;

    @Autowired
    public NickExistValidator(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(NickExist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(value != null && userRepository.existsByNick(value) == false){
            return true;
        }else
            return false;

    }
}
