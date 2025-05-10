package ca.eshopexpress.model.dto;

import ca.eshopexpress.model.entity.User;
import ca.eshopexpress.model.enums.OrderStatus;
import ca.eshopexpress.model.enums.Province;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing an order.
 * This class is used to transfer order-related data between different layers of the application,
 * such as the service and presentation layers.
 *
 * Contains customer information, order financials, delivery estimate, and payment method.
 *
 * @version 1.0
 * @author Daniel Hinbest
 * @since 2025-05-09
 */
public class OrderDto {

    // Fields...

    /** Unique identifier for the order. */
    private long id;

    /** User associated with the order. */
    private User user;

    /** The date and time when the order was placed. */
    private LocalDateTime orderDate;

    /** The current status of the order. */
    private OrderStatus status;

    /** The subtotal amount of the order (before taxes). */
    private BigDecimal subtotal;

    /** The tax amount applied to the order. */
    private BigDecimal tax;

    /** The total amount of the order (including taxes). */
    private BigDecimal total;

    /** First name of the customer. */
    private String firstName;

    /** Last name of the customer. */
    private String lastName;

    /** Email address of the customer. */
    private String email;

    /** Shipping address. */
    private String address;

    /** City of the shipping address. */
    private String city;

    /** Province of the shipping address. */
    private Province province;

    /** Postal code of the shipping address. */
    private String postalCode;

    /** Payment method used by the customer. */
    private String paymentMethod;

    /** Estimated delivery date of the order. */
    private LocalDate estimatedDelivery;

    /**
     * Default constructor for creating an empty OrderDto object.
     */
    public OrderDto() {}

    /**
     * Full constructor for creating a populated OrderDto object.
     *
     * @param id the unique identifier for the order
     * @param user the user who placed the order
     * @param orderDate the date and time when the order was placed
     * @param status the current status of the order
     * @param subtotal the subtotal before tax
     * @param tax the tax applied to the order
     * @param total the final total amount including tax
     * @param firstName the customer's first name
     * @param lastName the customer's last name
     * @param email the customer's email address
     * @param address the shipping address
     * @param city the city of the shipping address
     * @param province the province of the shipping address
     * @param postalCode the postal code of the shipping address
     * @param paymentMethod the payment method used for the order
     * @param estimatedDelivery the estimated delivery date
     */
    public OrderDto(long id, User user, LocalDateTime orderDate, OrderStatus status, BigDecimal subtotal,
                    BigDecimal tax, BigDecimal total, String firstName, String lastName, String email,
                    String address, String city, Province province, String postalCode, String paymentMethod,
                    LocalDate estimatedDelivery) {
        this.id = id;
        this.user = user;
        this.orderDate = orderDate;
        this.status = status;
        this.subtotal = subtotal;
        this.tax = tax;
        this.total = total;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.paymentMethod = paymentMethod;
        this.estimatedDelivery = estimatedDelivery;
    }

    /**
     * Gets the order ID.
     *
     * @return the unique identifier of the order
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the order ID.
     *
     * @param id the unique identifier to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the user who placed the order.
     *
     * @return the associated user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who placed the order.
     *
     * @param user the user to associate with the order
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the date and time when the order was placed.
     *
     * @return the order's timestamp
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the date and time when the order was placed.
     *
     * @param orderDate the timestamp to set
     */
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Gets the current status of the order.
     *
     * @return the order status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the order.
     *
     * @param status the order status to set
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * Gets the subtotal amount before tax.
     *
     * @return the subtotal amount
     */
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    /**
     * Sets the subtotal amount before tax.
     *
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * Gets the tax amount.
     *
     * @return the tax amount applied to the order
     */
    public BigDecimal getTax() {
        return tax;
    }

    /**
     * Sets the tax amount.
     *
     * @param tax the tax to set
     */
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    /**
     * Gets the total amount including tax.
     *
     * @return the total cost of the order
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Sets the total amount including tax.
     *
     * @param total the total cost to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * Gets the customer's first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the customer's first name.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the customer's last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the customer's last name.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the customer's email address.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the customer's email address.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the shipping address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the shipping address.
     *
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the city of the shipping address.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of the shipping address.
     *
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the province of the shipping address.
     *
     * @return the province
     */
    public Province getProvince() {
        return province;
    }

    /**
     * Sets the province of the shipping address.
     *
     * @param province the province to set
     */
    public void setProvince(Province province) {
        this.province = province;
    }

    /**
     * Gets the postal code of the shipping address.
     *
     * @return the postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code of the shipping address.
     *
     * @param postalCode the postal code to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets the payment method used for the order.
     *
     * @return the payment method
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets the payment method used for the order.
     *
     * @param paymentMethod the payment method to set
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Gets the estimated delivery date.
     *
     * @return the estimated delivery date
     */
    public LocalDate getEstimatedDelivery() {
        return estimatedDelivery;
    }

    /**
     * Sets the estimated delivery date.
     *
     * @param estimatedDelivery the estimated delivery date to set
     */
    public void setEstimatedDelivery(LocalDate estimatedDelivery) {
        this.estimatedDelivery = estimatedDelivery;
    }
}