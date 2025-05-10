package ca.eshopexpress.model.entity;

import ca.eshopexpress.model.enums.OrderStatus;
import ca.eshopexpress.model.enums.Province;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The class to represent the orders in the database
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-05-05
 */
@Entity
@Table(name = "orders")
public class Order {
    /**
     * Order ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * User that placed the order
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The date the order was placed
     */
    @Column(name = "order_date")
    private LocalDateTime orderDate;

    /**
     * The status of the order
     */
    private OrderStatus status;

    /**
     * The order subtotal
     */
    @Column(nullable = false)
    private BigDecimal subtotal;

    /**
     * The order's tax added
     */
    @Column(nullable = false)
    private BigDecimal tax;

    /**
     * The order's total cost
     */
    @Column(nullable = false)
    private BigDecimal total;

    /**
     * The order user's first name
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * The order user's last name
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * The order user's email
     */
    @Column(nullable = false)
    private String email;

    /**
     * The order address
     */
    @Column(nullable = false)
    private String address;

    /**
     * The order city
     */
    @Column(nullable = false)
    private String city;

    /**
     * The order province
     */
    @Column(nullable = false)
    private Province province;

    /**
     * The order postal code
     */
    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    /**
     * The order's payment method
     */
    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    /**
     * The order's estimated delivery
     */
    @Column(name = "estimated_delivery")
    private LocalDate estimatedDelivery;

    /**
     * Get the user ID
     * @return user ID
     */
    public long getId() {
        return id;
    }

    /**
     * Set the user ID
     * @param id user ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the user
     * @return the order's user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the user
     * @param user the order's user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Get the date and time of the order's placement
     * @return date and time the order was placed
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * Set the order's date and time
     * @param orderDate date and time order was placed
     */
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Get the order status
     * @return order status enum
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Set the status enum
     * @param status status enum
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * Get the order subtotal
     * @return subtotal
     */
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    /**
     * Set the order subtotal
     * @param subtotal order subtotal
     */
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * Get the tax added to the order
     * @return tax added onto the order
     */
    public BigDecimal getTax() {
        return tax;
    }

    /**
     * Set the tax added to the order
     * @param tax tax added to the order
     */
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    /**
     * Get the order's total cost
     * @return total cost
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Set the order's total cost
     * @param total total cost
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * Get the user's first name
     * @return user's first name
     */
    public String getFirstName() {
        return user.getFirstName();
    }

    /**
     * Set the user's first name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the user's last name
     * @return user's last name
     */
    public String getLastName() {
        return user.getLastName();
    }

    /**
     * Set the user's last name
     * @param lastName last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the user's email
     * @return user's email
     */
    public String getEmail() {
        return user.getEmail();
    }

    /**
     * Set the user's email
     * @param email the user's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Set the order's address
     * @return order address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the order address
     * @param address order address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the order city
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * Set the order city
     * @param city order city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get the order province
     * @return order province
     */
    public Province getProvince() {
        return province;
    }

    /**
     * Set the order province
     * @param province order province
     */
    public void setProvince(Province province) {
        this.province = province;
    }

    /**
     * Get the order postal code
     * @return order postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Set the postal code
     * @param postalCode the postal code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Get the payment method
     * @return payment method
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Set the payment method
     * @param paymentMethod
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Get the estimated delivery date
     * @return delivery date
     */
    public LocalDate getEstimatedDelivery() {
        return estimatedDelivery;
    }

    /**
     * Set the estimated delivery date
     * @param estimatedDelivery delivery date
     */
    public void setEstimatedDelivery(LocalDate estimatedDelivery) {
        this.estimatedDelivery = estimatedDelivery;
    }

    /**
     * The order class string
     * @return order string
     */
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", orderDate=" + orderDate +
                ", status=" + status +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", total=" + total +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", estimatedDelivery=" + estimatedDelivery +
                '}';
    }
}
