package vn.codegym.blogjpahibernate.repository;

import vn.codegym.blogjpahibernate.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

 @Repository
public interface ICustomerRepository extends PagingAndSortingRepository<Customer, Long> {
}