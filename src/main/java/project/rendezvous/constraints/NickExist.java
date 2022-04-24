package project.rendezvous.constraints;

import project.rendezvous.constraints.validators.NickExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NickExistValidator.class)
public @interface NickExist {

    String message() default "- ten pseudonim jest już zajęty.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
}
