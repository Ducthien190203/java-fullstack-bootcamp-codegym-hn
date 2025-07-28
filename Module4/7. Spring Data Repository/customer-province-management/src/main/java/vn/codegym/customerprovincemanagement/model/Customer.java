package vn.codegym.customerprovincemanagement.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Represents a Customer entity in the system.
 * This entity is mapped to the 'customer' table in the database.
 */
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "First name cannot be empty")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    /**
     * Default constructor for Customer.
     */
    public Customer() {
    }

    /**
     * Returns the ID of the customer.
     * @return The customer's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the customer.
     * @param id The ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the first name of the customer.
     * @return The customer's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the customer.
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the customer.
     * @return The customer's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the customer.
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the province associated with the customer.
     * @return The customer's province.
     */
    public Province getProvince() {
        return province;
    }

    /**
     * Sets the province for the customer.
     * @param province The province to set.
     */
    public void setProvince(Province province) {
        this.province = province;
    }
}
