package vn.codegym.validateregisterform.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

/**
 * Represents a User entity with validation constraints for registration.
 */
public class User {
    @NotBlank(message = "First name is required!")
    @Size(min = 5, max = 45, message = "First name must be between 5 and 45 characters!")
    private String firstName;

    @NotBlank(message = "Last name is required!")
    @Size(min = 5, max = 45, message = "Last name must be between 5 and 45 characters!")
    private String lastName;

    @Pattern(regexp = "^0[0-9]{9,10}$", message = "Phone number must be 10 or 11 digits and start with 0!")
    private String phoneNumber;

    @Min(value = 18, message = "Age must be at least 18!")
    private int age;

    @Email(message = "Invalid email format!")
    private String email;

    /**
     * Default constructor for User.
     */
    public User() {
    }

    /**
     * Constructs a new User with the specified details.
     *
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param phoneNumber The phone number of the user.
     * @param age The age of the user.
     * @param email The email address of the user.
     */
    public User(String firstName, String lastName, String phoneNumber, int age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.email = email;
    }

    /**
     * Gets the first name of the user.
     *
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     *
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     *
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the phone number of the user.
     *
     * @return The phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the user.
     *
     * @param phoneNumber The phone number to set.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the age of the user.
     *
     * @return The age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the user.
     *
     * @param age The age to set.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the email address of the user.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}