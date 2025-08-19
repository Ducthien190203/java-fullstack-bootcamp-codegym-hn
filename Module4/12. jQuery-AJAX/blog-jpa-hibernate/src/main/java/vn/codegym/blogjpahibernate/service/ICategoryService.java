package vn.codegym.blogjpahibernate.service;

import vn.codegym.blogjpahibernate.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICategoryService {
    Page<Category> findAll(Pageable pageable);

    Optional<Category> findById(Long id);

    void save(Category category);

    void remove(Long id);

    Iterable<Category> findAll();
}
