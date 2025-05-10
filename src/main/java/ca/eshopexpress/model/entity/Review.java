package ca.eshopexpress.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;

/**
 * The class to represent the reviews in the database
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-04-24
 */
@Entity
@Table(name = "reviews")
public class Review {
    /**
     * The review ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The game for the review
     */
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    /**
     * The user rating
     */
    @Column(nullable = false)
    @Min(1)
    @Max(5)
    private Integer rating;

    /**
     * The user comments
     */
    @Column
    private String comment;

    /**
     * The user that left the review
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The date and time the review was made
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Get the review ID
     * @return review ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the review ID
     * @param id review ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the reviewed game
     * @return game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Set the reviewed game
     * @param game the game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Get the game rating
     * @return game rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * Set the game rating
     * @param rating game rating
     */
    public void setRatingI(Integer rating) {
        this.rating = rating;
    }

    /**
     * Get the game comment
     * @return game comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Set the game comment
     * @param comment game comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Get the user
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the user
     * @param user game's user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Get the review timestamp
     * @return review timestamp
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Set the review timestamp
     * @param createdAt review timestamp
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
