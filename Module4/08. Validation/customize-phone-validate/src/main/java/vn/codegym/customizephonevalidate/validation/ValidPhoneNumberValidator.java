package vn.codegym.customizephonevalidate.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for the {@link ValidPhoneNumber} annotation.
 * Checks if a phone number starts with '0', has a length between 10 and 11 digits, and contains only digits.
 */
public class ValidPhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    /**
     * Initializes the validator in preparation for isValid calls.
     * @param constraintAnnotation The annotation instance for a given constraint declaration.
     */
    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {
        // No initialization needed
    }

    /**
     * Implements the validation logic.
     * @param phoneNumber The phone number string to validate.
     * @param context Context in which the constraint is evaluated.
     * @return {@code true} if the phone number is valid, {@code false} otherwise.
     */
    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false; // Phone number cannot be null or empty
        }
        // Regex: Starts with 0, length 10-11, contains only digits
        return phoneNumber.matches("^0[0-9]{9,10}$");
    }
}