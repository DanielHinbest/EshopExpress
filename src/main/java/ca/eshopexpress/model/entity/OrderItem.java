package ca.eshopexpress.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Represents an item within an order, which can be a game or accessory.
 */
@Entity
@Table(name = "order_items")
public class OrderItem {

    /**
     * Unique identifier for the order item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The order to which this item belongs.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /**
     * Type of the product: "GAME" or "ACCESSORY".
     */
    @Column(name = "product_type", nullable = false, length = 20)
    private String productType;

    /**
     * ID of the product (could refer to a game or accessory).
     */
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    /**
     * Name of the product.
     */
    @Column(nullable = false, length = 255)
    private String name;

    /**
     * Price per unit of the product.
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * Quantity of the product ordered.
     */
    @Column(nullable = false)
    private Integer quantity;

    /**
     * URL of the product image.
     */
    @Column(name = "image_url", length = 255)
    private String imageUrl;

    /**
     * Indicates whether the product is digital.
     */
    @Column(name = "is_digital")
    private Boolean isDigital = false;

    /**
     * The platform associated with this product, if applicable.
     */
    @ManyToOne
    @JoinColumn(name = "platform_id")
    private Platform platform;

    // Getters and setters

    /**
     * Gets the ID of this order item.
     * @return the order item ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of this order item.
     * @param id the order item ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the associated order.
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets the associated order.
     * @param order the order
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Gets the product type.
     * @return the product type
     */
    public String getProductType() {
        return productType;
    }

    /**
     * Sets the product type.
     * @param productType the product type
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * Gets the product ID.
     * @return the product ID
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * Sets the product ID.
     * @param productId the product ID
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * Gets the product name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name.
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price per unit.
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price per unit.
     * @param price the price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the quantity ordered.
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity ordered.
     * @param quantity the quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the image URL.
     * @return the image URL
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the image URL.
     * @param imageUrl the image URL
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Checks if the product is digital.
     * @return true if digital, false otherwise
     */
    public Boolean getIsDigital() {
        return isDigital;
    }

    /**
     * Sets whether the product is digital.
     * @param isDigital true if digital, false otherwise
     */
    public void setIsDigital(Boolean isDigital) {
        this.isDigital = isDigital;
    }

    /**
     * Gets the platform associated with this product.
     * @return the platform
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * Sets the platform associated with this product.
     * @param platform the platform
     */
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
}
