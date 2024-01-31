package com.nht.activitytrackerserver.validation;

import com.nht.activitytrackerserver.model.auth.request.RegisterRequestDto;
import com.nht.activitytrackerserver.validation.confirmpassword.ConfirmPasswordConstraintValidator;
import com.nht.activitytrackerserver.validation.password.PasswordConstraintValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class RegisterRequestDtoTest {

    private final PasswordConstraintValidator passwordValidator = new PasswordConstraintValidator();
    private final ConfirmPasswordConstraintValidator confirmPasswordValidator = new ConfirmPasswordConstraintValidator();

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void testValidDTOCreation() {
        RegisterRequestDto dto = RegisterRequestDto.builder()
                        .username("username")
                        .password("StrongPass123")
                        .confirmPassword("StrongPass123")
                        .email("test@example.com")
                        .name("John")
                        .surname("Doe")
                        .build();

        // Validate the RegisterDto
        Set<ConstraintViolation<RegisterRequestDto>> violations = validator.validate(dto);

        log.info("Valid Password Violations: {}", violations);

        // Ensure that there is at least one violation indicating a validation error
        assertTrue(violations.isEmpty(), "Password fields are valid");
    }

    @Test
    void testInvalidPasswordDTOCreation() {
        RegisterRequestDto dto = RegisterRequestDto.builder()
                        .username("username")
                        .password("weak")
                        .confirmPassword("weak")
                        .email("test@example.com")
                        .name("John")
                        .surname("Doe")
                        .build();

        // Validate the RegisterDto
        Set<ConstraintViolation<RegisterRequestDto>> violations = validator.validate(dto);

        log.info("Invalid Password Violations: {}", violations);

        // Ensure that there is at least one violation indicating a validation error
        assertFalse(violations.isEmpty(), "Invalid password should result in a validation error");
    }

    @Test
    void testInvalidConfirmPasswordDTOCreation() {
        RegisterRequestDto registerRequestDto = RegisterRequestDto.builder()
                .username("username")
                .password("StrongPass123")
                .confirmPassword("WrongPassword")
                .email("test@example.com")
                .name("John")
                .surname("Doe")
                .build();

        // Validate the RegisterDto
        Set<ConstraintViolation<RegisterRequestDto>> violations = validator.validate(registerRequestDto);

        log.info("Password Mismatch Violations: {}", violations);

        // Ensure that there is at least one violation indicating a validation error
        assertFalse(violations.isEmpty(), "Password mismatch should result in a validation error");

    }

    @Test
    void testValidPasswordValidation() {
        RegisterRequestDto registerRequestDto = RegisterRequestDto.builder()
                .username("username")
                .password("StrongPass123")
                .confirmPassword("StrongPass123")
                .email("test@example.com")
                .name("John")
                .surname("Doe")
                .build();

        assertTrue(passwordValidator.isValid(registerRequestDto.getPassword(), null), "Valid password should pass validation");
    }

    @Test
    void testInvalidPasswordValidation() {
        RegisterRequestDto registerRequestDto = RegisterRequestDto.builder()
                .username("username")
                .password("weakpass")
                .confirmPassword("weakpass")
                .email("test@example.com")
                .name("John")
                .surname("Doe")
                .build();

        assertFalse(passwordValidator.isValid(registerRequestDto.getPassword(), null), "Weak password should fail validation");
    }

    @Test
    void testValidConfirmPasswordValidation() {
        RegisterRequestDto registerRequestDto = RegisterRequestDto.builder()
                .username("username")
                .password("StrongPass123")
                .confirmPassword("StrongPass123")
                .email("test@example.com")
                .name("John")
                .surname("Doe")
                .build();

        assertTrue(confirmPasswordValidator.isValid(registerRequestDto, null), "Matching passwords should pass validation");
    }

    @Test
    void testInvalidConfirmPasswordValidation() {
        RegisterRequestDto registerRequestDto = RegisterRequestDto.builder()
                .username("username")
                .password("StrongPass123")
                .confirmPassword("WrongPassword")
                .email("test@example.com")
                .name("John")
                .surname("Doe")
                .build();

        assertFalse(confirmPasswordValidator.isValid(registerRequestDto, null), "Non-matching passwords should fail validation");
    }
}
