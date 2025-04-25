package ca.eshopexpress.model.entity;

import ca.eshopexpress.model.enums.AgeRating;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * The class to represent the games in the database
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-04-24
 */
@Entity
@Table(name = "games")
public class Game {
    /**
     * The game ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The game title
     */
    @Column(nullable = false)
    private String title;

    /**
     * The game description
     */
    @Column(length = 2000)
    private String description;

    /**
     * The game price
     */
    @Column(nullable = false)
    private BigDecimal price;

    /**
     * The game's cover image
     */
    private String coverImageUrl;

    /**
     * The game's release date
     */
    @Column(name = "release_date")
    private LocalDate releaseDate;

    /**
     * The game's publisher
     */
    private String publisher;

    /**
     * The game's developer
     */
    private String developer;

    /**
     * The game's age rating
     */
    private AgeRating ageRating;

    /**
     * Set of game genres
     */
    @ManyToMany
    @JoinTable(name = "game_genres", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    /**
     * Set of game platforms
     */
    @ManyToMany
    @JoinTable(name = "game_platforms", joinColumns = @JoinColumn(name = "game_id"), inverseJoinColumns = @JoinColumn(name = "platform_id"))
    private Set<Platform> platforms = new HashSet<>();

    /**
     * Set of game reviews
     */
    @OneToMany(mappedBy = "game")
    private Set<Review> reviews = new HashSet<>();

    /**
     * Boolean value for digital games
     */
    @Column(name = "is_digital")
    private boolean digital;

    /**
     * Game's stock quantity
     */
    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    /**
     * Game's average rating
     */
    @Column(name = "average_rating")
    private Double averageRating;

    /**
     * Get the game ID
     * @return game ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the game ID
     * @param id game ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the game title
     * @return game title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the game title
     * @param title game title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the game description
     * @return game description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the game description
     * @param description game description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the game price
     * @return game price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Set the game price
     * @param price game price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Get the cover image
     * @return cover image
     */
    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    /**
     * Set the cover image
     * @param coverImageUrl cover image
     */
    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    /**
     * Get the release date
     * @return release date
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    /**
     * Set the release date
     * @param releaseDate release date
     */
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Get the publisher
     * @return publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Set the publisher
     * @param publisher publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Get the developer
     * @return the developer
     */
    public String getDeveloper() {
        return developer;
    }

    /**
     * Set the developer
     * @param developer the developer
     */
    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    /**
     * Get the age rating
     * @return age rating
     */
    public AgeRating getAgeRating() {
        return ageRating;
    }

    /**
     * Set the age rating
     * @param ageRating age rating
     */
    public void setAgeRating(AgeRating ageRating) {
        this.ageRating = ageRating;
    }

    /**
     * Get the genres
     * @return set of genres
     */
    public Set<Genre> getGenres() {
        return genres;
    }

    /**
     * Set the genres
     * @param genres list of genres
     */
    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    /**
     * Get the platforms
     * @return set of platforms
     */
    public Set<Platform> getPlatforms() {
        return platforms;
    }

    /**
     * Set the platforms
     * @param platforms set of platforms
     */
    public void setPlatforms(Set<Platform> platforms) {
        this.platforms = platforms;
    }

    /**
     * Get the reviews
     * @return set of reviews
     */
    public Set<Review> getReviews() {
        return reviews;
    }

    /**
     * Set the reviews
     * @param reviews set of reviews
     */
    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * Check if digital
     * @return true/false status
     */
    public boolean isDigital() {
        return digital;
    }

    /**
     * Set the digital status
     * @param digital true/false status
     */
    public void setDigital(boolean digital) {
        this.digital = digital;
    }

    /**
     * Get the stock
     * @return stock quantity
     */
    public Integer getStockQuantity() {
        return stockQuantity;
    }

    /**
     * Set the stock
     * @param stockQuantity stock quantity
     */
    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    /**
     * Get the average rating
     * @return average rating
     */
    public Double getAverageRating() {
        return averageRating;
    }

    /**
     * Set the average rating
     * @param averageRating average rating
     */
    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    /**
     * Game class string
     * @return a game
     */
    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", coverImageUrl='" + coverImageUrl + '\'' +
                ", releaseDate=" + releaseDate +
                ", publisher='" + publisher + '\'' +
                ", developer='" + developer + '\'' +
                ", ageRating=" + ageRating +
                ", genres=" + genres +
                ", platforms=" + platforms +
                ", reviews=" + reviews +
                ", digital=" + digital +
                ", stockQuantity=" + stockQuantity +
                ", averageRating=" + averageRating +
                '}';
    }
}
