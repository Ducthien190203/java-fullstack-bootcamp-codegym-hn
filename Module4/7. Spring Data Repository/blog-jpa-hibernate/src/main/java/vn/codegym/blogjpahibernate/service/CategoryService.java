package vn.codegym.blogjpahibernate.service;

import vn.codegym.blogjpahibernate.model.Category;
import vn.codegym.blogjpahibernate.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Iterable<Category> findAll() {
        Iterable<Category> categories = categoryRepository.findAll();
        if (categories instanceof java.util.Collection) {
            System.out.println("CategoryService.findAll() (Iterable) returning " + ((java.util.Collection) categories).size() + " categories.");
        } else {
            System.out.println("CategoryService.findAll() (Iterable) returning an Iterable (not a Collection).");
        }
        return categories;
    }
}
