package vn.codegym.blogjpahibernate.repository;

import vn.codegym.blogjpahibernate.model.Blog;
import vn.codegym.blogjpahibernate.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepository extends PagingAndSortingRepository<Blog, Long> {
    Page<Blog> findAllByCategory(Category category, Pageable pageable);
    Page<Blog> findAllByTitleContaining(String title, Pageable pageable);
}
