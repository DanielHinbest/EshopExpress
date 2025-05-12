package ca.eshopexpress.repository;

import ca.eshopexpress.model.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link Platform} entities.
 * <p>
 * Provides CRUD operations and query methods to retrieve platforms from the database.
 * </p>
 *
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-05-11
 */
@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {

    /**
     * Retrieves a list of platforms whose names contain the specified string, ignoring case.
     *
     * @param name the name of the platform to search for
     * @return a list of matching platforms
     */
    List<Platform> findByNameContainingIgnoreCase(String name);
}
