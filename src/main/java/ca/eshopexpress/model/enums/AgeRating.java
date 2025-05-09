package ca.eshopexpress.model.enums;

public enum AgeRating {
    EVERYONE("E", "Everyone"),
    EVERYONE_TEN_PLUS("E10+", "Everyone 10+"),
    TEEN("T", "Teen"),
    MATURE("M17+", "Mature 17+"),
    ADULTS_ONLY("AO", "Adults Only 18+"),
    RATING_PENDING("RP", "Rating Pending"),
    RATING_PENDING_17_PLUS("RP17+", "Rating Pending - Likely Mature 17+");

    private final String code;
    private final String description;

    AgeRating(String code, String description) {
        this.code = code;
        this.description = description;
    }
}