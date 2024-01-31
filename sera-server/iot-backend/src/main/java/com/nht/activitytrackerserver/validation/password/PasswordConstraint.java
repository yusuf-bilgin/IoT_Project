package com.nht.activitytrackerserver.validation.password;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
    String message() default "Invalid password [A-Z, a-z, 0-9]";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

