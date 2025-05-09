package ca.eshopexpress.model.enums;

/**
 * Enum for each of the ESRB age ratings
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-05-09
 */
public enum AgeRating {
    /**
     * E rating
     */
    EVERYONE("E", "Everyone"),
    /**
     * E10+ rating
     */
    EVERYONE_TEN_PLUS("E10+", "Everyone 10+"),
    /**
     * T rating
     */
    TEEN("T", "Teen"),
    /**
     * M rating
     */
    MATURE("M", "Mature 17+"),
    /**
     * AO rating
     */
    ADULTS_ONLY("AO", "Adults Only 18+"),
    /**
     * RP rating
     */
    RATING_PENDING("RP", "Rating Pending"),
    /**
     * RP - 17+ rating
     */
    RATING_PENDING_17_PLUS("RPM", "Rating Pending - Likely Mature 17+");

    /**
     * Rating code
     */
    private final String code;
    /**
     * Rating description
     */
    private final String description;

    /**
     * Age rating constructor
     * @param code rating code
     * @param description rating description
     */
    AgeRating(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Get rating code
     * @return ESRB rating code
     */
    public String getCode() {
        return code;
    }

    /**
     * Get rating description
     * @return ESRB rating description
     */
    public String getDescription() {
        return description;
    }
}