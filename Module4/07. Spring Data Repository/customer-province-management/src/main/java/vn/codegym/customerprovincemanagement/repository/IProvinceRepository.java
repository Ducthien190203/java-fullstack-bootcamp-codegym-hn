package vn.codegym.customerprovincemanagement.repository;

import vn.codegym.customerprovincemanagement.model.Province;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for Province entities.
 * Extends CrudRepository to provide basic CRUD operations.
 */
public interface IProvinceRepository extends CrudRepository<Province, Long> {
}
