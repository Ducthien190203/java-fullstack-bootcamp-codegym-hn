package vn.codegym.customizephonevalidate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import vn.codegym.customizephonevalidate.model.PhoneNumber;

/**
 * Controller for handling phone number validation requests.
 */
@Controller
public class PhoneController {

    /**
     * Displays the phone number input form.
     * @param model The model to add attributes to.
     * @return The name of the view to render (index.html).
     */
    @GetMapping("/")
    public String showForm(Model model){
        model.addAttribute("phoneNumber", new PhoneNumber());
        return "index";
    }

    /**
     * Handles the submission of the phone number form and performs validation.
     * @param phoneNumber The PhoneNumber object bound from the form.
     * @param bindingResult The BindingResult object containing validation errors.
     * @param model The model to add attributes to.
     * @return The name of the view to render (index.html if errors, result.html otherwise).
     */
    @PostMapping("/")
    public String checkValidation( @Valid @ModelAttribute("phoneNumber") PhoneNumber phoneNumber,
                                  BindingResult bindingResult, Model model){
        if (bindingResult.hasFieldErrors()) {
            return "index";
        }
        model.addAttribute("phoneNumber", phoneNumber);
        return "result";
    }
}