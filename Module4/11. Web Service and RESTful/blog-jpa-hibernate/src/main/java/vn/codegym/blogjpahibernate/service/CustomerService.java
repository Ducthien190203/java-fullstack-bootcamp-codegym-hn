package vn.codegym.blogjpahibernate.service;

import vn.codegym.blogjpahibernate.model.Customer;
import vn.codegym.blogjpahibernate.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

 @Service
public class CustomerService implements ICustomerService{

    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Override
    public Iterable<Customer> findAll() {
       return iCustomerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
       return iCustomerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
       return iCustomerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
       iCustomerRepository.deleteById(id);
    }
}