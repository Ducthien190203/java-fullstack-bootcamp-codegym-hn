package vn.codegym.customerprovincemanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.codegym.customerprovincemanagement.model.Customer;
import vn.codegym.customerprovincemanagement.model.Province;

/**
 * Repository interface for Customer entities.
 * Extends PagingAndSortingRepository to provide CRUD operations with pagination and sorting capabilities.
 */
public interface ICustomerRepository extends PagingAndSortingRepository<Customer, Long> {
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
