package ca.eshopexpress.repository;

import ca.eshopexpress.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link Review} entities.
 * <p>
 * Provides CRUD operations and query methods for managing game reviews.
 * </p>
 *
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-05-11
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    /**
     * Retrieves a list of reviews for a specific game.
     *
     * @param gameId the ID of the game to retrieve reviews for
     * @return a list of reviews for the game
     */
    List<Review> findByGameId(Long gameId);

    /**
     * Retrieves a list of reviews written by a specific user.
     *
     * @param userId the ID of the user whose reviews are being retrieved
     * @return a list of reviews written by the user
     */
    List<Review> findByUserId(Long userId);

    /**
     * Retrieves a review for a specific game by a specific user.
     * Ensures that a user can only leave one review per game.
     *
     * @param gameId the ID of the game
     * @param userId the ID of the user
     * @return the review matching both game and user
     */
    Review findByGameIdAndUserId(Long gameId, Long userId);
}
