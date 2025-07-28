package vn.codegym.customizephonevalidate.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Custom annotation for validating phone numbers.
 * The phone number must start with '0', have a length between 10 and 11 digits, and contain only digits.
 */
@Documented
@Constraint(validatedBy = ValidPhoneNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPhoneNumber {
    /**
     * The default error message if validation fails.
     * @return The error message key.
     */
    String message() default "{phoneNumber.invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}