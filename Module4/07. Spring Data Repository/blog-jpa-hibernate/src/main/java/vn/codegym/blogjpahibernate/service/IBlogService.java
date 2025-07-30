package vn.codegym.blogjpahibernate.service;

import vn.codegym.blogjpahibernate.model.Blog;
import vn.codegym.blogjpahibernate.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBlogService {
    Page<Blog> findAll(Pageable pageable);

    Optional<Blog> findById(Long id);

    void save(Blog blog);

    void remove(Long id);

    Page<Blog> findAllByCategory(Category category, Pageable pageable);
    Page<Blog> findAllByTitleContaining(String title, Pageable pageable);
}
