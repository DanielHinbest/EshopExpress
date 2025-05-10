package ca.eshopexpress.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The class to represent the users in the database
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-04-24
 */
@Entity
@Table(name = "users")
public class User {
    /**
     * User ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Username
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * User's password
     */
    @Column(nullable = false)
    private String password;

    /**
     * User's email
     */
    @Column(nullable = false)
    private String email;

    /**
     * User's first name
     */
    private String firstName;

    /**
     * User's last name
     */
    private String lastName;

    /**
     * User's roles
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    /**
     * User's orders
     */
    @OneToMany(mappedBy = "users")
    private Set<Order> orders = new HashSet<>();

    /**
     * User's reviews
     */
    @OneToMany(mappedBy = "users")
    private List<Review> reviews = new ArrayList<>();

    /**
     * User's wishlist
     */
    @OneToMany(mappedBy = "users")
    private List<WishlistItem> wishlistItems = new ArrayList<>();

    /**
     * Get the user ID
     * @return user ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the user ID
     * @param id user ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the user password
     * @return user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the user password
     * @param password user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the user's name
     * @return user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the user's email
     * @param email user's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the user's first name
     * @return user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the user's first name
     * @param firstName user's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the user's last name
     * @return user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the user's last name
     * @param lastName user's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get a list of the user's roles
     * @return list of user's roles
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Set the user's roles
     * @param roles list of user's roles
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Get a list of the user's orders
     * @return list of the user's orders
     */
    public Set<Order> getOrders() {
        return orders;
    }

    /**
     * Set a list of user's orders
     * @param orders the user's orders
     */
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    /**
     * Get the user's reviews
     * @return the user's reviews
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * Set the user's reviews
     * @param reviews the user's reviews
     */
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * Get the wishlist items
     * @return the wishlist items
     */
    public List<WishlistItem> getWishlistItems() {
        return wishlistItems;
    }

    /**
     * Set the wishlist items
     * @param wishlistItems the wishlist items
     */
    public void setWishlistItems(List<WishlistItem> wishlistItems) {
        this.wishlistItems = wishlistItems;
    }

    /**
     * A string of the user details
     * @return user details
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roles=" + roles +
                ", orders=" + orders +
                ", reviews=" + reviews +
                ", wishlistItems=" + wishlistItems +
                '}';
    }
}
