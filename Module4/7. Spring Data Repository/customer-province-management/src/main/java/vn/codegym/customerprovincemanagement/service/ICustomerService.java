package vn.codegym.customerprovincemanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.customerprovincemanagement.model.Customer;
import vn.codegym.customerprovincemanagement.model.Province;

import java.util.Optional;

/**
 * Service interface for managing Customer entities.
 * Defines business logic operations related to customers.
 */
public interface ICustomerService {
    /**
     * Retrieves a page of all customers.
     * @param pageable Pagination information.
     * @return A Page of Customer entities.
     */
    Page<Customer> findAll(Pageable pageable);

    /**
     * Saves a customer entity.
     * @param customer The customer to be saved.
     */
    void save(Customer customer);

    /**
     * Finds a customer by its ID.
     * @param id The ID of the customer to find.
     * @return An Optional containing the customer if found, otherwise empty.
     */
    Optional<Customer> findById(Long id);

    /**
     * Removes a customer by its ID.
     * @param id The ID of the customer to remove.
     */
    void remove(Long id);

    /**
     * Finds all customers belonging to a specific province.
     * @param province The province to search for.
     * @return An Iterable of customers in the specified province.
     */
    Iterable<Customer> findAllByProvince(Province province);

    /**
     * Finds a page of customers whose first name contains the given string.
     * @param firstname The string to search for in the first name.
     * @param pageable The pagination information.
     * @return A Page of customers matching the search criteria.
     */
    Page<Customer> findAllByFirstNameContaining(String firstname, Pageable pageable);
}
