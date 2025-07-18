package vn.codegym.thuchanh2.service.impl;

import vn.codegym.thuchanh2.dao.CustomerDAO;
import vn.codegym.thuchanh2.model.Customer;
import vn.codegym.thuchanh2.service.CustomerService;

import java.util.List;

public class SimpleCustomerServiceImpl implements CustomerService {
    private CustomerDAO customerDAO = new CustomerDAO();

    @Override
    public List<Customer> findAll() {
        return CustomerDAO.getAllCustomer();
    }

    @Override
    public Customer findById(int id) {
        return customerDAO.getCustomerById(id);
    }

    @Override
    public void save(Customer customer) {
        customerDAO.save(customer);
    }

    @Override
    public int getNextAvailableId() {
        return CustomerDAO.getNextAvaibleId();
    }
}