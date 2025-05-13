package ca.eshopexpress.service;

import ca.eshopexpress.model.entity.User;
import ca.eshopexpress.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing users within the system. It includes operations
 * for retrieving, creating, updating, and deleting user data, as well as enabling
 * and disabling user accounts.
 *
 * @version 1.0
 * @since 2025-05-12
 */
public interface UserService {

    /**
     * Retrieves a list of all users in the system.
     *
     * @return a list of all users
     */
    List<User> findAllUsers();

    /**
     * Finds a user by their ID.
     *
     * @param id the ID of the user to search for
     * @return an Optional containing the user if found, otherwise empty
     */
    Optional<User> findUserById(Long id);

    /**
     * Retrieves a user by their ID or throws an exception if not found.
     *
     * @param id the ID of the user
     * @return the user if found
     * @throws ResourceNotFoundException if the user is not found
     */
    User getUserOrThrow(Long id);

    /**
     * Finds users by their first name using case-insensitive partial matching.
     *
     * @param firstName the first name to search for
     * @return a list of users with the matching first name
     */
    List<User> findByFirstName(String firstName);

    /**
     * Finds users by their last name using case-insensitive partial matching.
     *
     * @param lastName the last name to search for
     * @return a list of users with the matching last name
     */
    List<User> findByLastName(String lastName);

    /**
     * Finds users based on the city they reside in.
     *
     * @param city the name of the city to search for
     * @return a list of users living in the specified city
     */
    List<User> findByCity(String city);

    /**
     * Saves a new user or updates an existing one.
     *
     * @param user the user object to save or update
     * @return the saved or updated user
     */
    User save(User user);

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     */
    void deleteById(Long id);
}
