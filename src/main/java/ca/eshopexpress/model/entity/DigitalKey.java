package ca.eshopexpress.model.entity;

import jakarta.persistence.*;

/**
 * The class to represent the digital keys in the database
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-05-08
 */
@Entity
@Table(name = "digital_keys")
public class DigitalKey {
    /**
     * Digital key ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Game for Digital ID
     */
    @OneToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    /**
     * Game activation key
     */
    @Column(name = "activation_key", nullable = false, unique = true)
    private String activationKey;

    /**
     * Digital key activation status
     */
    @Column
    private String status;

    /**
     * Platform for digital key
     */
    @OneToOne
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;

    /**
     * Order item for digital key
     */
    @OneToOne
    @JoinColumn(name = "order_item_id", nullable = false)
    private OrderItem orderItem;

    /**
     * Get the digital key ID
     * @return digital key ID
     */
    public long getId() {
        return id;
    }

    /**
     * Set the digital key ID
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the game
     * @return the digital game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Set the game
     * @param game the digital game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Get the activation key
     * @return activation key
     */
    public String getActivationKey() {
        return activationKey;
    }

    /**
     * Set the activation key
     * @param activationKey activation key
     */
    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    /**
     * Get the activation status
     * @return digital key status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the activation status
     * @param status digital key status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Get the game platform
     * @return game platform
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * Set the game platform
     * @param platform game platform
     */
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    /**
     * Get the order item
     * @return order item
     */
    public OrderItem getOrderItem() {
        return orderItem;
    }

    /**
     * Set the order item
     * @param orderItem order item
     */
    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }
}
