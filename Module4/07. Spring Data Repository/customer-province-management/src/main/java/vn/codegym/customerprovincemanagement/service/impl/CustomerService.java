package vn.codegym.customerprovincemanagement.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.codegym.customerprovincemanagement.model.Customer;
import vn.codegym.customerprovincemanagement.model.Province;
import vn.codegym.customerprovincemanagement.repository.ICustomerRepository;
import vn.codegym.customerprovincemanagement.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the ICustomerService interface.
 * Provides business logic for Customer entities.
 */
@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository iCustomerRepository;

    /**
     * Retrieves a page of all customers.
     * @param pageable Pagination information.
     * @return A Page of Customer entities.
     */
    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return iCustomerRepository.findAll(pageable);
    }

    /**
     * Saves a customer entity.
     * @param customer The customer to be saved.
     */
    @Override
    public void save(Customer customer) {
        iCustomerRepository.save(customer);
    }

    /**
     * Finds a customer by its ID.
     * @param id The ID of the customer to find.
     * @return An Optional containing the customer if found, otherwise empty.
     */
    @Override
    public Optional<Customer> findById(Long id) {
        return iCustomerRepository.findById(id);
    }

    /**
     * Removes a customer by its ID.
     * @param id The ID of the customer to remove.
     */
    @Override
    public void remove(Long id) {
        iCustomerRepository.deleteById(id);
    }

    /**
     * Finds all customers belonging to a specific province.
     * @param province The province to search for.
     * @return An Iterable of customers in the specified province.
     */
    @Override
    public Iterable<Customer> findAllByProvince(Province province) {
        return iCustomerRepository.findAllByProvince(province);
    }

    /**
     * Finds a page of customers whose first name contains the given string.
     * @param firstname The string to search for in the first name.
     * @param pageable The pagination information.
     * @return A Page of customers matching the search criteria.
     */
    @Override
    public Page<Customer> findAllByFirstNameContaining(String firstname, Pageable pageable) {
        return iCustomerRepository.findAllByFirstNameContaining(firstname, pageable);
    }
}
