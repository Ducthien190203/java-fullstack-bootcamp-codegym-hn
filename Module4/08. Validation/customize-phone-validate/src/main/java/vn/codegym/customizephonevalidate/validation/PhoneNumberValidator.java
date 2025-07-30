package vn.codegym.customizephonevalidate.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<Phone, String> {

    @Override
    public void initialize(Phone constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.isEmpty()) {
            return false; // Not empty
        }
        // Starts with 0, length 10-11, contains only digits
        return s.matches("^0[0-9]{9,10}$");
    }
}