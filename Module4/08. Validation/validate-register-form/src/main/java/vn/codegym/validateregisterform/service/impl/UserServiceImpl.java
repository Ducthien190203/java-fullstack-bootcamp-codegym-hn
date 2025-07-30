package vn.codegym.validateregisterform.service.impl;

import vn.codegym.validateregisterform.model.User;
import vn.codegym.validateregisterform.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of the {@link UserService} interface.
 * This service manages User entities in an in-memory list.
 */
public class UserServiceImpl implements UserService {
    /**
     * In-memory storage for users. Note: This list is static and not thread-safe for concurrent access.
     * For production environments, a proper database or concurrent collection should be used.
     */
    private static List<User> users;

    static {
        users = new ArrayList<>();
    }

    /**
     * Saves a given User entity to the in-memory list.
     * Throws {@link NullPointerException} if the user is null.
     *
     * @param user The User entity to be saved. Must not be null.
     */
    @Override
    public void save(User user) {
        Objects.requireNonNull(user, "User to be saved cannot be null");
        users.add(user);
    }

    /**
     * Retrieves all User entities from the in-memory list.
     *
     * @return A list of all User entities.
     */
    @Override
    public List<User> findAll() {
        return new ArrayList<>(users); // Return a copy to prevent external modification
    }
}