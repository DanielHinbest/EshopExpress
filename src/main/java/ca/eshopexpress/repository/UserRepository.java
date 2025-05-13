package ca.eshopexpress.repository;

import ca.eshopexpress.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link User} entities in the database.
 * Extends {@link JpaRepository} to provide CRUD operations and custom queries.
 * Provides methods to retrieve users by various attributes such as ID, last name,
 * and city. Also allows for enabling and disabling user accounts.
 *
 * @version 1.0
 * @since 2025-05-12
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their ID.
     *
     * @param id the ID of the user to find
     * @return an Optional containing the user if found, otherwise empty
     */
    Optional<User> findUserById(Long id);

    /**
     * Finds users by their first name using case-insensitive matching.
     *
     * @param firstName the first name to search for
     * @return a list of users matching the given first name
     */
    List<User> findByFirstNameContainingIgnoreCase(String FirstName);

    /**
     * Finds users by their last name using case-insensitive matching.
     *
     * @param lastName the last name to search for
     * @return a list of users matching the given last name
     */
    List<User> findByLastNameContainingIgnoreCase(String lastName);

    /**
     * Finds users by the city they live in.
     *
     * @param city the city to search for
     * @return a list of users living in the specified city
     */
    List<User> findByCityContainingIgnoreCase(String city);

    /**
     * Finds a user by their email address.
     *
     * @param email the email to search for
     * @return an Optional containing the user if found, otherwise empty
     */
    Optional<User> findByEmail(String email);

    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return an Optional containing the user if found, otherwise empty
     */
    Optional<User> findByUsername(String username);

    /**
     * Saves a given user entity (either creating a new user or updating an existing one).
     *
     * @param user the user entity to save
     * @return the saved user entity
     */
    @Override
    User save(User user);

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     */
    @Override
    void deleteById(Long id);
}
