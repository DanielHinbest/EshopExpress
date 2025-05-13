package ca.eshopexpress.service.impl;

import ca.eshopexpress.exception.ResourceNotFoundException;
import ca.eshopexpress.model.entity.User;
import ca.eshopexpress.repository.UserRepository;
import ca.eshopexpress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link UserService} interface.
 * Provides business logic for managing user accounts, including CRUD operations
 * and methods for enabling or disabling user accounts based on their ID.
 *
 * @version 1.0
 * @since 2025-05-12
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all users from the repository.
     *
     * @return a list of all users
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return an Optional containing the user if found, otherwise empty
     */
    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    /**
     * Retrieves a user by their ID or throws a {@link ResourceNotFoundException} if not found.
     *
     * @param id the ID of the user to retrieve
     * @return the user if found
     * @throws ResourceNotFoundException if no user with the specified ID is found
     */
    @Override
    public User getUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    /**
     * Finds users by their first name using case-insensitive matching.
     *
     * @param firstName the first name to search for
     * @return a list of users with the given first name
     */
    public List<User> findByFirstName(String firstName) {
        return userRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    /**
     * Finds users by their last name using case-insensitive matching.
     *
     * @param lastName the last name to search for
     * @return a list of users with the given last name
     */
    @Override
    public List<User> findByLastName(String lastName) {
        return userRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    /**
     * Finds users by the city they live in using case-insensitive matching.
     *
     * @param city the city to search for
     * @return a list of users living in the specified city
     */
    @Override
    public List<User> findByCity(String city) {
        return userRepository.findByCityContainingIgnoreCase(city);
    }

    /**
     * Saves a user (either creates a new user or updates an existing one).
     * Evicts all cached entries associated with "users" after saving.
     *
     * @param user the user to save
     * @return the saved user entity
     */
    @Override
    @CacheEvict(value = "users", allEntries = true)
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes a user by their ID.
     * Evicts all cached entries associated with "users" after deletion.
     *
     * @param id the ID of the user to delete
     */
    @Override
    @CacheEvict(value = "users", allEntries = true)
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}

