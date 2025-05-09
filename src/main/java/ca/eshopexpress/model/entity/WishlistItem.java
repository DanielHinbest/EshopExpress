package ca.eshopexpress.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * The class to represent the wishlist items in the database
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-05-08
 */
@Entity
@Table(name = "wishlist_items")
public class WishlistItem {
    /**
     * The wishlist item ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The user's wishlist
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The game in the wishlist
     */
    @OneToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    /**
     * The date and time the item was added
     */
    @Column(name = "added_at")
    private LocalDateTime addedAt;

    /**
     * Get the item ID
     * @return wishlist item ID
     */
    public long getId() {
        return id;
    }

    /**
     * Set the item ID
     * @param id wishlist item ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the user
     * @return user for the wishlist
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the user
     * @param user set the wishlist user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Get the wishlist game
     * @return wishlist game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Set the wishlist game
     * @param game wishlist game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Get the time added
     * @return time added to wishlist
     */
    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    /**
     * Set the time added
     * @param addedAt added to wishlist
     */
    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }
}
