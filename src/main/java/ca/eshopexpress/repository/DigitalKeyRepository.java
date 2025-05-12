package ca.eshopexpress.repository;

import ca.eshopexpress.model.entity.DigitalKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link DigitalKey} entities.
 * <p>
 * Provides CRUD operations and query methods to retrieve digital keys for games.
 * </p>
 *
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-05-11
 */
@Repository
public interface DigitalKeyRepository extends JpaRepository<DigitalKey, Long> {

    /**
     * Retrieves a list of digital keys for a specific game.
     *
     * @param gameId the ID of the game for which digital keys are being searched
     * @return a list of digital keys associated with the game
     */
    List<DigitalKey> findByGameId(Long gameId);

    /**
     * Retrieves a list of digital keys for a specific platform.
     *
     * @param platformId the ID of the platform to filter digital keys
     * @return a list of digital keys available for the specified platform
     */
    List<DigitalKey> findByPlatformId(Long platformId);

    /**
     * Retrieves a list of digital keys with the specified status.
     *
     * @param status the status of the digital key (e.g., AVAILABLE, SOLD, RESERVED)
     * @return a list of digital keys with the given status
     */
    List<DigitalKey> findByStatus(String status);

    /**
     * Retrieves a digital key by its unique activation key.
     *
     * @param activationKey the activation key to search for
     * @return the digital key matching the activation key
     */
    DigitalKey findByActivationKey(String activationKey);
}
