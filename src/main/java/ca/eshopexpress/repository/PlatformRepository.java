package ca.eshopexpress.repository;

import ca.eshopexpress.model.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link Platform} entities.
 * <p>
 * Provides CRUD operations and query methods to retrieve platforms from the database.
 * </p>
 *
 * @author Daniel Hinbest
 * @version 1.1
 * @since 2025-05-11
 */
@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {

    /**
     * Retrieves a platform whose name contains the specified string, ignoring case.
     * Modified to return an Optional to handle possible absence of matching platform.
     *
     * @param name the name of the platform to search for
     * @return an Optional containing the matching platform if found, or empty otherwise
     */
    Optional<Platform> findByNameContainingIgnoreCase(String name);
}