package vn.codegym.customizephonevalidate.model;

import vn.codegym.customizephonevalidate.validation.ValidPhoneNumber;

/**
 * Represents a phone number model for validation.
 */
public class PhoneNumber {
    /**
     * The phone number string.
     */
    @ValidPhoneNumber
    private String number;

    /**
     * Gets the phone number.
     * @return The phone number string.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the phone number.
     * @param number The phone number string to set.
     */
    public void setNumber(String number) {
        this.number = number;
    }
}