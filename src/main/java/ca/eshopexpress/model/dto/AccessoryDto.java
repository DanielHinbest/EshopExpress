package ca.eshopexpress.model.dto;

import ca.eshopexpress.model.entity.Accessory;
import ca.eshopexpress.model.entity.Platform;
import ca.eshopexpress.model.enums.AccessoryType;

import java.math.BigDecimal;

/**
 * The class to represent the Accessory entity in the website
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-05-09
 */
public class AccessoryDto {
    /**
     * Accessory ID
     */
    private Long id;
    /**
     * Accessory name
     */
    private String name;
    /**
     * Accessory description
     */
    private String description;
    /**
     * Accessory price
     */
    private BigDecimal price;
    /**
     * Image URL
     */
    private String imageUrl;
    /**
     * Type of accessory
     */
    private AccessoryType accessoryType;
    /**
     * Accessory brand
     */
    private String brand;
    /**
     * Accessory model
     */
    private String model;
    /**
     * Quantity in stock
     */
    private Integer stockQuantity;
    /**
     * Accessory platform
     */
    private Platform platform;

    /**
     * Empty constructor for an accessory
     */
    public AccessoryDto() {}

    /**
     * Parameterized constructor for an accessory
     * @param id accessory ID
     * @param name accessory name
     * @param description accessory description
     * @param price price
     * @param imageUrl image of accessory
     * @param accessoryType type of accessory
     * @param brand brand of accessory
     * @param model model of accessory
     * @param stockQuantity quantity in stock
     * @param platform platform for accessory
     */
    public AccessoryDto(Long id, String name, String description, BigDecimal price, String imageUrl, AccessoryType accessoryType, String brand, String model, Integer stockQuantity, Platform platform) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.accessoryType = accessoryType;
        this.brand = brand;
        this.model = model;
        this.stockQuantity = stockQuantity;
        this.platform = platform;
    }

    /**
     * Get the ID
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the ID
     * @param id accessory ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name
     * @param name accessory name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the description
     * @return accessory description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description
     * @param description accessory description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the price
     * @return accessory price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Set the price
     * @param price accessory price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Get the image
     * @return accessory image
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Set the image
     * @param imageUrl accessory image
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Get the accessory type
     * @return accessory type
     */
    public AccessoryType getAccessoryType() {
        return accessoryType;
    }

    /**
     * Set the accessory type
     * @param accessoryType accessory type
     */
    public void setAccessoryType(AccessoryType accessoryType) {
        this.accessoryType = accessoryType;
    }

    /**
     * Get the brand
     * @return accessory brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Set the brand
     * @param brand accessory brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Get the model
     * @return accessory model
     */
    public String getModel() {
        return model;
    }

    /**
     * Set the model
     * @param model accessory model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Get the stock
     * @return quantity available
     */
    public Integer getStockQuantity() {
        return stockQuantity;
    }

    /**
     * Set the stock
     * @param stockQuantity quantity available
     */
    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    /**
     * Get the platform
     * @return intended platform
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * Set the platform
     * @param platform intended platform
     */
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    /**
     * An example of the structure of an AccessoryDto
     * @return an AccessoryDto example
     */
    public static AccessoryDto example() {
        return new AccessoryDto(1L, "Nintendo Switch Pro Controller", "Handheld Controller for Nintendo Switch", 80.00, null, AccessoryType.CONTROLLER, "Nintendo", null, 5, new Platform());
    }
}
