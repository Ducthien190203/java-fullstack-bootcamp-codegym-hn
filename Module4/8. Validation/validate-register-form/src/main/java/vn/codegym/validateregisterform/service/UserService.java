package vn.codegym.validateregisterform.service;

import vn.codegym.validateregisterform.model.User;

import java.util.List;

/**
 * Service interface for managing User entities.
 */
public interface UserService {
    /**
     * Saves a given User entity.
     *
     * @param user The User entity to be saved.
     */
    void save(User user);

    /**
     * Retrieves all User entities.
     *
     * @return A list of all User entities.
     */
    List<User> findAll();
}