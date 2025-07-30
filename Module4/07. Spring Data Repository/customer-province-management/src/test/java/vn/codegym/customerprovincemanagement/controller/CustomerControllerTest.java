package vn.codegym.customerprovincemanagement.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.ModelAndView;
import vn.codegym.customerprovincemanagement.model.Customer;
import vn.codegym.customerprovincemanagement.service.ICustomerService;
import vn.codegym.customerprovincemanagement.repository.IProvinceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private ICustomerService customerService;

    @Mock
    private IProvinceRepository provinceRepository;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListCustomerNoSearch() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        Page<Customer> customerPage = new PageImpl<>(customers, pageable, customers.size());

        when(customerService.findAll(pageable)).thenReturn(customerPage);

        ModelAndView modelAndView = customerController.listCustomer(Optional.empty(), pageable);

        assertEquals("/customer/list", modelAndView.getViewName());
        assertEquals(customerPage, modelAndView.getModel().get("customers"));
        verify(customerService, times(1)).findAll(pageable);
        verify(customerService, never()).findAllByFirstNameContaining(anyString(), any(Pageable.class));
    }

    @Test
    void testListCustomerWithSearch() {
        Pageable pageable = PageRequest.of(0, 10);
        String searchName = "John";
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        Page<Customer> customerPage = new PageImpl<>(customers, pageable, customers.size());

        when(customerService.findAllByFirstNameContaining(searchName, pageable)).thenReturn(customerPage);

        ModelAndView modelAndView = customerController.listCustomer(Optional.of(searchName), pageable);

        assertEquals("/customer/list", modelAndView.getViewName());
        assertEquals(customerPage, modelAndView.getModel().get("customers"));
        verify(customerService, times(1)).findAllByFirstNameContaining(searchName, pageable);
        verify(customerService, never()).findAll(any(Pageable.class));
    }
}
