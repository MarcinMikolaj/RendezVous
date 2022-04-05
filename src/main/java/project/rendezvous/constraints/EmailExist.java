package project.rendezvous.constraints;

import project.rendezvous.constraints.validators.EmailExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

//Checks if the email provided as value is assigned to any account in the database
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailExistValidator.class)
public @interface EmailExist {

    String message() default "this email already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
}
