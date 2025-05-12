package ca.eshopexpress.repository;

import ca.eshopexpress.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link Genre} entities.
 * <p>
 * Provides CRUD operations and query methods to retrieve genres from the database.
 * </p>
 *
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-05-11
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    /**
     * Retrieves a list of genres whose names contain the specified string, ignoring case.
     *
     * @param name the name of the genre to search for
     * @return a list of matching genres
     */
    List<Genre> findByNameContainingIgnoreCase(String name);
}
