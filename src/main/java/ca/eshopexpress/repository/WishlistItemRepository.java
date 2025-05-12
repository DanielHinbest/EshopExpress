package ca.eshopexpress.repository;

import ca.eshopexpress.model.entity.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link WishlistItem} entities.
 * <p>
 * Provides CRUD operations and query methods for managing items in a user's wishlist.
 * </p>
 *
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-05-11
 */
@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {

    /**
     * Retrieves a list of wishlist items for a specific user.
     *
     * @param userId the ID of the user whose wishlist items are being retrieved
     * @return a list of items in the user's wishlist
     */
    List<WishlistItem> findByUserId(Long userId);

    /**
     * Retrieves a wishlist item by user and game ID.
     * Ensures that a user cannot add the same game to the wishlist multiple times.
     *
     * @param userId the ID of the user
     * @param gameId the ID of the game in the wishlist
     * @return the wishlist item matching both user and game
     */
    WishlistItem findByUserIdAndGameId(Long userId, Long gameId);
}
