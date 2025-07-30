package vn.codegym.blogjpahibernate.repository;

import vn.codegym.blogjpahibernate.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
}
