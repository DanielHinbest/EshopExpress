package ca.eshopexpress.model.entity;

import ca.eshopexpress.model.enums.AccessoryType;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "accessories")
public class Accessory {
    /**
     * Accessory ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Accessory name
     */
    @Column(nullable = false)
    private String name;

    /**
     * Accessory description
     */
    @Column(length = 2000)
    private String description;

    /**
     * Accessory price
     */
    @Column(nullable = false)
    private BigDecimal price;

    /**
     * Accessory image URL
     */
    private String imageUrl;

    /**
     * Accessory type
     */
    @Column(nullable = false)
    private AccessoryType type;

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
    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    /**
     * Accessory's platform
     */
    @OneToOne
    @JoinTable(name = "accessory_platforms", joinColumns = @JoinColumn(name = "accessory_id"), inverseJoinColumns = @JoinColumn(name = "platform_id"))
    private Platform platform;

    /**
     * Get the accessory ID
     * @return accessory ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the accessory ID
     * @param id accessory ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the accessory name
     * @return accessory name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the accessory name
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
     * Get the image URL
     * @return image URL
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Set the image URL
     * @param imageUrl image URL
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Get the accessory type
     * @return accessory type enum
     */
    public AccessoryType getType() {
        return type;
    }

    /**
     * Set the accessory type
     * @param type accessory type enum
     */
    public void setType(AccessoryType type) {
        this.type = type;
    }

    /**
     * Get the brand
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Set the brand
     * @param brand the brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Get the model
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * Set the model
     * @param model the model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Get the stock quantity
     * @return stock quantity
     */
    public Integer getStockQuantity() {
        return stockQuantity;
    }

    /**
     * Set the stock quantity
     * @param stockQuantity stock quantity
     */
    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    /**
     * Get the platform
     * @return accessory platform
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * Set the accessory platform
     * @param platform accessory platform
     */
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    /**
     * Output string for the accessory class
     * @return class output string
     */
    @Override
    public String toString() {
        return "Accessory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", type=" + type +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", platform=" + platform +
                '}';
    }
}
