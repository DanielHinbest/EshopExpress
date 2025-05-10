package ca.eshopexpress.model.dto;

import ca.eshopexpress.model.entity.Game;
import ca.eshopexpress.model.entity.User;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing a review for a game.
 * Encapsulates information about a user's review, including rating, comment,
 * creation time, and associated game and user data.
 *
 * @version 1.0
 * @since 2025-05-09
 */
public class ReviewDto {

    /** Unique identifier of the review. */
    private long id;

    /** The game being reviewed. */
    private Game game;

    /** Rating given to the game (typically on a scale of 1 to 5). */
    private Integer rating;

    /** Textual comment provided by the user in the review. */
    private String comment;

    /** The user who submitted the review. */
    private User user;

    /** The timestamp when the review was created. */
    private LocalDateTime createdAt;

    /**
     * Default constructor for creating an empty ReviewDto object.
     */
    public ReviewDto() {}

    /**
     * Constructs a ReviewDto with all attributes.
     *
     * @param id the unique identifier for the review
     * @param game the game associated with the review
     * @param rating the numeric rating assigned by the user
     * @param comment the user's written feedback
     * @param user the user who submitted the review
     * @param createdAt the date and time the review was created
     */
    public ReviewDto(long id, Game game, Integer rating, String comment, User user, LocalDateTime createdAt) {
        this.id = id;
        this.game = game;
        this.rating = rating;
        this.comment = comment;
        this.user = user;
        this.createdAt = createdAt;
    }

    /**
     * Gets the review ID.
     *
     * @return the unique identifier of the review
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the review ID.
     *
     * @param id the unique identifier to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the game that was reviewed.
     *
     * @return the associated game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Sets the game associated with the review.
     *
     * @param game the game to associate
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Gets the numeric rating given to the game.
     *
     * @return the rating value
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * Sets the numeric rating for the review.
     *
     * @param rating the rating value to set
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * Gets the comment provided in the review.
     *
     * @return the user's comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment for the review.
     *
     * @param comment the text of the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets the user who submitted the review.
     *
     * @return the reviewing user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with the review.
     *
     * @param user the user to associate
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the timestamp when the review was created.
     *
     * @return the creation time of the review
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when the review was created.
     *
     * @param createdAt the creation time to set
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}