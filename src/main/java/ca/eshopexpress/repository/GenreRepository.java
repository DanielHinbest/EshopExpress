package ca.eshopexpress.repository;

import ca.eshopexpress.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link Genre} entities.
 * <p>
 * Provides CRUD operations and query methods to retrieve genres from the database.
 * </p>
 *
 * @author Daniel Hinbest
 * @version 1.1
 * @since 2025-05-11
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    /**
     * Retrieves a genre whose name contains the specified string, ignoring case.
     * Modified to return an Optional to handle possible absence of matching genre.
     *
     * @param name the name of the genre to search for
     * @return an Optional containing the matching genre if found, or empty otherwise
     */
    Optional<Genre> findByNameContainingIgnoreCase(String name);
}