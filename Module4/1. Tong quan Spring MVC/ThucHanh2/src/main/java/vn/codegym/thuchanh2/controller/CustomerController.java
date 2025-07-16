package vn.codegym.thuchanh2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.codegym.thuchanh2.model.Customer;
import vn.codegym.thuchanh2.service.CustomerService;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/") // Thêm phương thức này để chuyển hướng từ root URL
    public String redirectToCustomers() {
        return "redirect:/customers";
    }

    @GetMapping("/customers")
    public String showList(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customer-list"; // Trả về tên view, không cần .jsp
    }

    @GetMapping("/customers/view")
    public String viewCustomer(Model model, @RequestParam("id") int id) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer-detail"; // Trả về tên view, không cần .jsp
    }
}