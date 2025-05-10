package ca.eshopexpress.model.dto;

import ca.eshopexpress.model.entity.Order;
import ca.eshopexpress.model.entity.Review;
import ca.eshopexpress.model.entity.Role;
import ca.eshopexpress.model.entity.WishlistItem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Data Transfer Object (DTO) for handling user registration data.
 * This class includes credentials, personal information, and related entities such as
 * roles, orders, reviews, and wishlist items.
 *
 * @version 1.0
 * @since 2025-05-09
 */
public class UserRegistrationDto {

    /** The unique identifier of the user. */
    private Long id;

    /** The username chosen by the user. */
    private String username;

    /** The user's password. */
    private String password;

    /** The email address of the user. */
    private String email;

    /** The user's first name. */
    private String firstName;

    /** The user's last name. */
    private String lastName;

    /** The set of roles assigned to the user. */
    private Set<Role> roles = new HashSet<>();

    /** The set of orders made by the user. */
    private Set<Order> orders = new HashSet<>();

    /** The list of reviews made by the user. */
    private List<Review> reviews = new ArrayList<>();

    /** The list of items in the user's wishlist. */
    private List<WishlistItem> wishlistItems = new ArrayList<>();

    /**
     * Default constructor for creating an empty UserRegistrationDto.
     */
    public UserRegistrationDto() {}

    /**
     * Constructs a UserRegistrationDto with all attributes.
     *
     * @param id the unique identifier of the user
     * @param username the username of the user
     * @param password the user's password
     * @param email the user's email address
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param roles the roles assigned to the user
     * @param orders the user's orders
     * @param reviews the reviews written by the user
     * @param wishlistItems the user's wishlist items
     */
    public UserRegistrationDto(Long id, String username, String password, String email, String firstName, String lastName,
                               Set<Role> roles, Set<Order> orders, List<Review> reviews, List<WishlistItem> wishlistItems) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.orders = orders;
        this.reviews = reviews;
        this.wishlistItems = wishlistItems;
    }

    /**
     * Gets the user's ID.
     *
     * @return the user ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the user's ID.
     *
     * @param id the user ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the user's username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's first name.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the user's last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user's last name.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the set of roles assigned to the user.
     *
     * @return the user's roles
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Sets the set of roles for the user.
     *
     * @param roles the roles to assign
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Gets the set of orders made by the user.
     *
     * @return the user's orders
     */
    public Set<Order> getOrders() {
        return orders;
    }

    /**
     * Sets the user's orders.
     *
     * @param orders the orders to assign
     */
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    /**
     * Gets the list of reviews written by the user.
     *
     * @return the user's reviews
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * Sets the list of reviews for the user.
     *
     * @param reviews the reviews to assign
     */
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * Gets the user's wishlist items.
     *
     * @return the list of wishlist items
     */
    public List<WishlistItem> getWishlistItems() {
        return wishlistItems;
    }

    /**
     * Sets the user's wishlist items.
     *
     * @param wishlistItems the wishlist items to assign
     */
    public void setWishlistItems(List<WishlistItem> wishlistItems) {
        this.wishlistItems = wishlistItems;
    }
}
