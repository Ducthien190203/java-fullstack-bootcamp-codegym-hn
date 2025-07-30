package vn.codegym.customerprovincemanagement.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import vn.codegym.customerprovincemanagement.model.Customer;
import vn.codegym.customerprovincemanagement.model.Province;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IProvinceRepository provinceRepository;

    @Test
    void testFindAllByFirstNameContaining() {
        // Given
        Province province1 = new Province();
        province1.setName("Hanoi");
        provinceRepository.save(province1);

        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setProvince(province1);
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Jane");
        customer2.setLastName("Smith");
        customer2.setProvince(province1);
        customerRepository.save(customer2);

        Customer customer3 = new Customer();
        customer3.setFirstName("Johnny");
        customer3.setLastName("Depp");
        customer3.setProvince(province1);
        customerRepository.save(customer3);

        Pageable pageable = PageRequest.of(0, 10);

        // When
        Page<Customer> customers = customerRepository.findAllByFirstNameContaining("John", pageable);

        // Then
        assertThat(customers).isNotNull();
        assertThat(customers.getTotalElements()).isEqualTo(2);
        assertThat(customers.getContent()).extracting(Customer::getFirstName).containsExactlyInAnyOrder("John", "Johnny");
    }
}
