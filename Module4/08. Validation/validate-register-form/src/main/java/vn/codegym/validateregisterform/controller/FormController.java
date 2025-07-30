package vn.codegym.validateregisterform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import vn.codegym.validateregisterform.model.User;
import vn.codegym.validateregisterform.service.UserService;

import javax.validation.Valid;

/**
 * Controller for handling user registration forms.
 */
@Controller
public class FormController {

    @Autowired
    private UserService userService;

    /**
     * Displays the user registration form.
     *
     * @param model The model to add attributes to.
     * @return The name of the view to render (index.html).
     */
    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    /**
     * Processes the user registration form submission.
     * Validates the user input and saves the user if valid.
     *
     * @param user The User object populated from the form.
     * @param bindingResult The result of the validation.
     * @param model The model to add attributes to.
     * @return The name of the view to render (index.html if errors, result.html if successful).
     */
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        userService.save(user);
        model.addAttribute("user", user);
        return "result";
    }
}