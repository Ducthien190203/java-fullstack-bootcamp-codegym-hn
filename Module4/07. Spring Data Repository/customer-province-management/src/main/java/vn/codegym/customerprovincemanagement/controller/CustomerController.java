package vn.codegym.customerprovincemanagement.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import vn.codegym.customerprovincemanagement.model.Customer;
import vn.codegym.customerprovincemanagement.model.Province;
import vn.codegym.customerprovincemanagement.repository.IProvinceRepository;
import vn.codegym.customerprovincemanagement.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Controller for managing Customer related operations.
 * Handles requests for listing, creating, updating, and deleting customers.
 */
@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IProvinceRepository provinceRepository;

    /**
     * Provides a list of all provinces to the model for use in forms.
     * @return An Iterable of Province entities.
     */
    @ModelAttribute("provinces")
    public Iterable<Province> listProvinces() {
        return provinceRepository.findAll();
    }

    /**
     * Displays a paginated list of customers, with optional search functionality.
     * @param search An Optional string for searching customers by first name.
     * @param pageable Pagination information.
     * @return ModelAndView for the customer list page.
     */
    @GetMapping
    public ModelAndView listCustomer(@RequestParam("search") Optional<String> search, Pageable pageable){
        Page<Customer> customers;
        if(search.isPresent()){
            customers = customerService.findAllByFirstNameContaining(search.get(), pageable);
        } else {
            customers = customerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        modelAndView.addObject("search", search.orElse(null));
        return modelAndView;
    }

    /**
     * Displays the form for creating a new customer.
     * @return ModelAndView for the customer creation form.
     */
    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    /**
     * Handles the submission of the customer creation form.
     * Validates the customer data and saves it to the database.
     * @param customer The Customer object to be created.
     * @param bindingResult Contains the results of the validation.
     * @param redirectAttributes Used to add flash attributes for redirection.
     * @return Redirects to the customer list page on success, or back to the form on error.
     */
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("customer") Customer customer,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/customer/create";
        }
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("message", "Create new customer successfully");
        return "redirect:/customers";
    }

    /**
     * Displays the form for updating an existing customer.
     * @param id The ID of the customer to update.
     * @return ModelAndView for the customer update form, or error page if customer not found.
     */
    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/customer/update");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        }
        return new ModelAndView("/error_404");
    }

    /**
     * Handles the submission of the customer update form.
     * Validates the updated customer data and saves it to the database.
     * @param customer The Customer object with updated data.
     * @param bindingResult Contains the results of the validation.
     * @param redirect Used to add flash attributes for redirection.
     * @return Redirects to the customer list page on success, or back to the form on error.
     */
    @PostMapping("/update/{id}")
    public String update(@Valid @ModelAttribute("customer") Customer customer,
                         BindingResult bindingResult,
                         RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return "/customer/update";
        }
        customerService.save(customer);
        redirect.addFlashAttribute("message", "Update customer successfully");
        return "redirect:/customers";
    }

    /**
     * Deletes a customer by their ID.
     * @param id The ID of the customer to delete.
     * @param redirect Used to add flash attributes for redirection.
     * @return Redirects to the customer list page.
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirect) {
        customerService.remove(id);
        redirect.addFlashAttribute("message", "Delete customer successfully");
        return "redirect:/customers";
    }
}
