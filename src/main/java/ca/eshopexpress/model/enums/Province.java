package ca.eshopexpress.model.enums;

/**
 * Enum for each of the 10 provinces and 3 territories of Canada
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-05-09
 */
public enum Province {
    /**
     * Province of Alberta
     */
    ALBERTA("AB", "Alberta"),
    /**
     * Province of British Columbia
     */
    BRITISH_COLUMBIA("BC", "BRITISH_COLUMBIA"),
    /**
     * Province of Manitoba
     */
    MANITOBA("MB", "Manitoba"),
    /**
     * Province of New Brunswick
     */
    NEW_BRUNSWICK("NB", "New Brunswick"),
    /**
     * Territory of Northwest Territories
     */
    NORTHWEST_TERRITORIES("NT", "Northwest Territories"),
    /**
     * Territory of Nunavut
     */
    NUNAVUT("NU", "Nunavut"),
    /**
     * Province of Newfoundland and Labrador
     */
    NEWFOUNDLAND_AND_LABRADOR("NL", "Newfoundland and Labrador"),
    /**
     * Province of Nova Scotia
     */
    NOVA_SCOTIA("NS", "Nova Scotia"),
    /**
     * Province of Ontario
     */
    ONTARIO("ON", "Ontario"),
    /**
     * Province of Prince Edward Island
     */
    PRINCE_EDWARD_ISLAND("PE", "Prince Edward Island"),
    /**
     * Province of Quebec
     */
    QUEBEC("QC", "Quebec"),
    /**
     * Province of Saskatchewan
     */
    SASKATCHEWAN("SK", "Saskatchewan"),
    /**
     * Territory of Yukon
     */
    YUKON("YU", "Yukon");

    /**
     * Each province's code abbreviation
     */
    private final String code;
    /**
     * Each province's name
     */
    private final String name;

    /**
     * Constructor for the province enum
     * @param code province/territory abbreviation
     * @param name province/territory name
     */
    Province(String code, String name) {
        this.code = code;
        this.name = name;
    }
}