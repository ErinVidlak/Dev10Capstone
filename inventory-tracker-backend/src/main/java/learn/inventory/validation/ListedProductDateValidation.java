package learn.inventory.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})                          // 2
@Retention(RUNTIME)
@Constraint(validatedBy = {ListedProductDateValidator.class}) // 3
@Documented
public @interface ListedProductDateValidation {
    String message() default "{Date sold must be the same or after date listed}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
