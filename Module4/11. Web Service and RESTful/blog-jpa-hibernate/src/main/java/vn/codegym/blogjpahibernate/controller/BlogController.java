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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/blogs")
    public ResponseEntity<Page<Blog>> listBlogs(@RequestParam(value = "s", required = false) Optional<String> s, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Blog> blogs;
        if (s.isPresent()) {
            blogs = blogService.findAllByTitleContaining(s.get(), pageable);
        } else {
            blogs = blogService.findAll(pageable);
        }
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @PostMapping("/blogs")
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
        Optional<Category> categoryOptional = categoryService.findById(blog.getCategory().getId());
        categoryOptional.ifPresent(blog::setCategory);
        blogService.save(blog);
        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<Blog> viewBlog(@PathVariable Long id) {
        Optional<Blog> blogOptional = blogService.findById(id);
        return blogOptional.map(blog -> new ResponseEntity<>(blog, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/blogs/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody Blog blog) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (blogOptional.isPresent()) {
            blog.setId(id);
            Optional<Category> categoryOptional = categoryService.findById(blog.getCategory().getId());
            categoryOptional.ifPresent(blog::setCategory);
            blogService.save(blog);
            return new ResponseEntity<>(blog, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/blogs/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        Optional<Blog> blogOptional = blogService.findById(id);
        if (blogOptional.isPresent()) {
            blogService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/blogs/category/{id}")
    public ResponseEntity<Page<Blog>> listBlogsByCategory(@PathVariable Long id, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (categoryOptional.isPresent()) {
            Page<Blog> blogs = blogService.findAllByCategory(categoryOptional.get(), pageable);
            return new ResponseEntity<>(blogs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
