package project.rendezvous.constraints;

import project.rendezvous.constraints.validators.EmailValidator;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {

    String message() default "- musisz podać poprawny adres email.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
}
