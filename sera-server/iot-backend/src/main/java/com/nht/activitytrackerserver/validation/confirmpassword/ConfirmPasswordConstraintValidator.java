package com.nht.activitytrackerserver.validation.confirmpassword;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class ConfirmPasswordConstraintValidator implements ConstraintValidator<ConfirmPasswordConstraint, Object> {


    @Override
    public void initialize(ConfirmPasswordConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        // Access password field from the validated object
        String password = null;
        String confirmPassword = null;
        try {
            Field passwordField = obj.getClass().getDeclaredField("password");
            passwordField.setAccessible(true);
            password = (String) passwordField.get(obj);

            Field confirmField = obj.getClass().getDeclaredField("confirmPassword");
            confirmField.setAccessible(true);
            confirmPassword = (String) confirmField.get(obj);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return password.equals(confirmPassword);
    }
}
