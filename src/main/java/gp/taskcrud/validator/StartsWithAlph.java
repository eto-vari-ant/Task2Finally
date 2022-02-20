package gp.taskcrud.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = NameValidation.class)
@Documented
public @interface StartsWithAlph {
    String message() default "Your name should starts with the letter";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
