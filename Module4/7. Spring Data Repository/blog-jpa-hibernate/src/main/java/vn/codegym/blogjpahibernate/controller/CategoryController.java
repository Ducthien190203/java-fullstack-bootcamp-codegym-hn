package vn.codegym.blogjpahibernate.controller;

import vn.codegym.blogjpahibernate.model.Category;
import vn.codegym.blogjpahibernate.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ModelAndView listCategories(@PageableDefault(size = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("category/list");
        modelAndView.addObject("categories", categoryService.findAll(pageable));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("category/create");
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("message", "New category created successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (categoryOptional.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("category/edit");
            modelAndView.addObject("category", categoryOptional.get());
            return modelAndView;
        } else {
            return new ModelAndView("error.404");
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("category/edit");
        modelAndView.addObject("category", category);
        modelAndView.addObject("message", "Category updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (categoryOptional.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("category/delete");
            modelAndView.addObject("category", categoryOptional.get());
            return modelAndView;
        } else {
            return new ModelAndView("error.404");
        }
    }

    @PostMapping("/delete")
    public String deleteCategory(@ModelAttribute("category") Category category) {
        categoryService.remove(category.getId());
        return "redirect:/categories";
    }
}