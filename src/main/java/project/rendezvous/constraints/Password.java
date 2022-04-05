package project.rendezvous.constraints;

import project.rendezvous.constraints.validators.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//  This is Constraint for Password
//  At least one digit [0-9]
//  At least one lowercase character [a-z]
//  At least one uppercase character [A-Z]
//  At least one special character [!@#&()–[{}]:;',?/*~$^+=<>]
//  At least 8 characters in length, but no more than 20.


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {

    String message() default "Invalid password provided";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
    boolean isEnable() default true;

}
