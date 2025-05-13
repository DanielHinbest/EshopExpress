package ca.eshopexpress.service;

import ca.eshopexpress.model.entity.Order;

import java.util.List;

/**
 * Service interface for managing {@link Order} entities.
 * <p>
 * Defines operations for retrieving orders based on various customer attributes.
 * </p>
 *
 * @author Daniel
 * @version 1.0
 * @since 2025-05-13
 */
public interface OrderService {

    /**
     * Retrieves all orders in the system.
     *
     * @return a list of all orders
     */
    List<Order> findAllOrders();

    /**
     * Retrieves a specific order by its ID.
     *
     * @param id the ID of the order
     * @return the order with the specified ID
     */
    Order findOrderById(Long id);

    /**
     * Retrieves all orders placed by a specific customer based on their customer ID.
     *
     * @param customerId the ID of the customer
     * @return a list of orders associated with the customer
     */
    List<Order> findOrdersByCustomerId(Long customerId);

    /**
     * Retrieves orders placed by customers with the specified last name.
     *
     * @param lastName the last name of the customer
     * @return a list of matching orders
     */
    List<Order> findByLastName(String lastName);

    /**
     * Retrieves orders placed by customers with the specified first name.
     *
     * @param firstName the first name of the customer
     * @return a list of matching orders
     */
    List<Order> findByFirstName(String firstName);

    /**
     * Retrieves orders placed using a specific email address.
     *
     * @param email the email used in the order
     * @return a list of matching orders
     */
    List<Order> findByEmail(String email);

    /**
     * Retrieves orders placed by customers with the specified full name.
     *
     * @param firstName the first name of the customer
     * @param lastName  the last name of the customer
     * @return a list of matching orders
     */
    List<Order> findByFullName(String firstName, String lastName);
}
