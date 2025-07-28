package vn.codegym.blogjpahibernate.controller;

import vn.codegym.blogjpahibernate.model.Blog;
import vn.codegym.blogjpahibernate.model.Category;
import vn.codegym.blogjpahibernate.service.IBlogService;
import vn.codegym.blogjpahibernate.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping("/blogs")
    public ModelAndView listBlogs(@RequestParam("s") Optional<String> s, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Blog> blogs;
        if (s.isPresent()) {
            blogs = blogService.findAllByTitleContaining(s.get(), pageable);
        } else {
            blogs = blogService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @GetMapping("/create-blog")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("blog", new Blog());
        Iterable<Category> categories = categoryService.findAll();
        if (categories instanceof java.util.Collection) {
            System.out.println("BlogController.showCreateForm() passing " + ((java.util.Collection) categories).size() + " categories to view.");
        } else {
            System.out.println("BlogController.showCreateForm() passing an Iterable (not a Collection) to view.");
        }
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @PostMapping("/create-blog")
    public String saveBlog(@ModelAttribute("blog") Blog blog) {
        // Lấy Category đầy đủ từ DB trước khi lưu Blog
        Optional<Category> categoryOptional = categoryService.findById(blog.getCategory().getId());
        categoryOptional.ifPresent(blog::setCategory);
        blogService.save(blog);
        return "redirect:/create-blog"; // Redirect to create blog page to show the form with message
    }

    @GetMapping("/view-blog/{id}")
    public ModelAndView viewBlog(@PathVariable Long id) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (blogOptional.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("view");
            modelAndView.addObject("blog", blogOptional.get());
            return modelAndView;
        } else {
            return new ModelAndView("error.404");
        }
    }

    @GetMapping("/edit-blog/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (blogOptional.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("edit");
            modelAndView.addObject("blog", blogOptional.get());
            return modelAndView;
        } else {
            return new ModelAndView("error.404");
        }
    }

    @PostMapping("/edit-blog")
    public ModelAndView updateBlog(@ModelAttribute("blog") Blog blog) {
        // Lấy Category đầy đủ từ DB trước khi lưu Blog
        Optional<Category> categoryOptional = categoryService.findById(blog.getCategory().getId());
        categoryOptional.ifPresent(blog::setCategory);
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("blog", blog);
        modelAndView.addObject("message", "Blog updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-blog/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (blogOptional.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("delete");
            modelAndView.addObject("blog", blogOptional.get());
            return modelAndView;
        } else {
            return new ModelAndView("error.404");
        }
    }

    @PostMapping("/delete-blog")
    public String deleteBlog(@ModelAttribute("blog") Blog blog) {
        blogService.remove(blog.getId());
        return "redirect:/blogs";
    }

    @GetMapping("/blogs/category/{id}")
    public ModelAndView listBlogsByCategory(@PathVariable Long id, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (categoryOptional.isPresent()) {
            Page<Blog> blogs = blogService.findAllByCategory(categoryOptional.get(), pageable);
            ModelAndView modelAndView = new ModelAndView("list");
            modelAndView.addObject("blogs", blogs);
            modelAndView.addObject("category", categoryOptional.get());
            return modelAndView;
        } else {
            return new ModelAndView("error.404");
        }
    }
}