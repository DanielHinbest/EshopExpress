package ca.eshopexpress.model.dto;

import ca.eshopexpress.model.enums.AgeRating;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * The class to represent the Game entity in the website
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-05-08
 */
public class GameDto {
    /**
     * Game ID
     */
    private Long id;
    /**
     * Game title
     */
    private String title;
    /**
     * Game description
     */
    private String description;
    /**
     * Game price
     */
    private BigDecimal price;
    /**
     * Game cover URL
     */
    private String coverImageUrl;
    /**
     * Game release date
     */
    private LocalDate releaseDate;
    /**
     * Game publisher
     */
    private String publisher;
    /**
     * Game developer
     */
    private String developer;
    /**
     * Game age rating
     */
    private AgeRating ageRating;
    /**
     * Game genres
     */
    private Set<String> genreNames = new HashSet<>();
    /**
     * Game platforms
     */
    private Set<String> platformNames = new HashSet<>();
    /**
     * Digital or physical
     */
    private boolean digital;
    /**
     * Stock quantity
     */
    private Integer stockQuantity;
    /**
     * Game rating
     */
    private Double averageRating;

    /**
     * Default constructor for empty GameDto
     */
    public GameDto() {}

    /**
     * Parameterized constructor for populated GameDto
     * @param id game ID
     * @param title game title
     * @param description game description
     * @param price game price
     * @param coverImageUrl cover image
     * @param releaseDate game's release date
     * @param publisher game publisher
     * @param developer game developer
     * @param ageRating ESRB rating
     * @param genreNames game genres
     * @param platformNames platforms available
     * @param digital digital or physical
     * @param stockQuantity quantity in stock
     * @param averageRating average game rating
     */
    public GameDto(Long id, String title, String description, BigDecimal price, String coverImageUrl,
                   LocalDate releaseDate, String publisher, String developer, AgeRating ageRating,
                   Set<String> genreNames, Set<String> platformNames, boolean digital,
                   Integer stockQuantity, Double averageRating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.coverImageUrl = coverImageUrl;
        this.releaseDate = releaseDate;
        this.publisher = publisher;
        this.developer = developer;
        this.ageRating = ageRating;
        this.genreNames = genreNames;
        this.platformNames = platformNames;
        this.digital = digital;
        this.stockQuantity = stockQuantity;
        this.averageRating = averageRating;
    }

    /**
     * Return the game ID
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
     * Return the game title
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
     * Return the game description
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
     * Return the game price
     * @return game price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Set the game price
     * @param price price of the game
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Return the cover image
     * @return coverImageUrl cover image
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
     * Return the release date
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
     * Return the publisher
     * @return game publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Set the publisher
     * @param publisher game publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Get the game developer
     * @return game developer
     */
    public String getDeveloper() {
        return developer;
    }

    /**
     * Set the game developer
     * @param developer game developer
     */
    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    /**
     * Get the game's age rating
     * @return ESRB age rating
     */
    public AgeRating getAgeRating() {
        return ageRating;
    }

    /**
     * Set the age rating
     * @param ageRating ESRB rating
     */
    public void setAgeRating(AgeRating ageRating) {
        this.ageRating = ageRating;
    }

    /**
     * Get the game genres
     * @return game genres
     */
    public Set<String> getGenreNames() {
        return genreNames;
    }

    /**
     * Set the game genres
     * @param genreNames game's genres
     */
    public void setGenreNames(Set<String> genreNames) {
        this.genreNames = genreNames;
    }

    /**
     * Get the game platforms
     * @return game platforms
     */
    public Set<String> getPlatformNames() {
        return platformNames;
    }

    /**
     * Set the game platforms
     * @param platformNames game platforms
     */
    public void setPlatformNames(Set<String> platformNames) {
        this.platformNames = platformNames;
    }

    /**
     * Check if the game is digital
     * @return digital status
     */
    public boolean isDigital() {
        return digital;
    }

    /**
     * Set the digital boolean
     * @param digital digital boolean
     */
    public void setDigital(boolean digital) {
        this.digital = digital;
    }

    /**
     * Get the stock quantity
     * @return stock quantity
     */
    public Integer getStockQuantity() {
        return stockQuantity;
    }

    /**
     * Set the quantity in stock
     * @param stockQuantity quantity in stock
     */
    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    /**
     * Get the average user rating
     * @return average user rating
     */
    public Double getAverageRating() {
        return averageRating;
    }

    /**
     * Set the average user rating
     * @param averageRating average user rating
     */
    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    /**
     * Example of the structure of a GameDto
     * @return a GameDto example
     */
    public static GameDto example() {
        Set<String> genres = new HashSet<>();
        genres.add("Simulation");
        genres.add("RPG");

        Set<String> platforms = new HashSet<>();
        platforms.add("PlayStation 5");
        platforms.add("PC");
        platforms.add("Xbox Series S/X");

        return new GameDto(
                1L,
                "The Sims 4",
                "An expansive open-world simulation by Electronic Arts",
                new BigDecimal("59.99"),
                "/images/sims-4.png",
                LocalDate.of(2014, 9, 2),
                "Electronic Arts",
                "Maxis",
                AgeRating.TEEN,
                genres,
                platforms,
                true,
                120,
                4.7
        );
    }
}
