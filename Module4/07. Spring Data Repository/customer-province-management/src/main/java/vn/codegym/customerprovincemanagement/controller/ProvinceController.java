package vn.codegym.customerprovincemanagement.controller;

import vn.codegym.customerprovincemanagement.model.Customer;
import vn.codegym.customerprovincemanagement.model.Province;
import vn.codegym.customerprovincemanagement.repository.IProvinceRepository;
import vn.codegym.customerprovincemanagement.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

/**
 * Controller for managing Province related operations.
 * Handles requests for listing, creating, updating, and viewing provinces.
 */
@Controller
@RequestMapping("/provinces")
public class ProvinceController {
    /**
     * Injects the Province repository for data access.
     */
    @Autowired
    private IProvinceRepository provinceRepository;

    /**
     * Injects the Customer service for business logic related to customers.
     */
    @Autowired
    private ICustomerService customerService;

    /**
     * Displays a list of all provinces.
     * @return ModelAndView for the province list page.
     */
    @GetMapping
    public ModelAndView listProvince() {
        ModelAndView modelAndView = new ModelAndView("/province/list");
        Iterable<Province> provinces = provinceRepository.findAll();
        modelAndView.addObject("provinces", provinces);
        return modelAndView;
    }

    /**
     * Displays the form for creating a new province.
     * @return ModelAndView for the province creation form.
     */
    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    /**
     * Handles the submission of the province creation form.
     * Saves the new province to the database.
     * @param province The Province object to be created.
     * @param redirectAttributes Used to add flash attributes for redirection.
     * @return Redirects to the province list page.
     */
    @PostMapping("/create")
    public String create(@ModelAttribute("province") Province province,
                         RedirectAttributes redirectAttributes) {
        provinceRepository.save(province);
        redirectAttributes.addFlashAttribute("message", "Create new province successfully");
        return "redirect:/provinces";
    }

    /**
     * Displays the form for updating an existing province.
     * @param id The ID of the province to update.
     * @return ModelAndView for the province update form, or error page if province not found.
     */
    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Province> province = provinceRepository.findById(id);
        if (province.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/province/update");
            modelAndView.addObject("province", province.get());
            return modelAndView;
        }
        return new ModelAndView("/error_404");
    }

    /**
     * Handles the submission of the province update form.
     * Saves the updated province data to the database.
     * @param province The Province object with updated data.
     * @param redirect Used to add flash attributes for redirection.
     * @return Redirects to the province list page.
     */
    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("province") Province province,
                         RedirectAttributes redirect) {
        provinceRepository.save(province);
        redirect.addFlashAttribute("message", "Update province successfully");
        return "redirect:/provinces";
    }

    /**
     * Displays customers belonging to a specific province.
     * @param id The ID of the province to view customers for.
     * @return ModelAndView for the customer list page filtered by province, or error page if province not found.
     */
    @GetMapping("/view-province/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id) {
        Optional<Province> provinceOptional = provinceRepository.findById(id);
        if (!provinceOptional.isPresent()) {
            return new ModelAndView("/error_404");
        }

        Iterable<Customer> customers = customerService.findAllByProvince(provinceOptional.get());

        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
}

