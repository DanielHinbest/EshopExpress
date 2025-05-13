package ca.eshopexpress.service.impl;

import ca.eshopexpress.exception.ResourceNotFoundException;
import ca.eshopexpress.model.entity.Order;
import ca.eshopexpress.repository.OrderRepository;
import ca.eshopexpress.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the {@link OrderService} interface for managing order-related operations.
 * <p>
 * Provides methods for retrieving orders by various customer criteria including ID, name, and email.
 * Caching is used to optimize read-heavy operations where applicable.
 * </p>
 *
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-05-13
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    /**
     * Constructs an OrderServiceImpl with the provided {@link OrderRepository}.
     *
     * @param orderRepository the repository used to interact with order data
     */
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Retrieves all orders in the system.
     * Results are cached to improve performance for repeated queries.
     *
     * @return a list of all orders
     */
    @Override
    @Cacheable("orders")
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Retrieves a specific order by its unique ID.
     * Result is cached by ID to optimize frequent lookups.
     *
     * @param id the ID of the order
     * @return the order with the specified ID
     * @throws ResourceNotFoundException if no order is found with the given ID
     */
    @Override
    @Cacheable(value = "orders", key = "#id")
    public Order findOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }

    /**
     * Retrieves all orders placed by a specific customer based on their customer ID.
     *
     * @param customerId the ID of the customer
     * @return a list of orders associated with the customer
     */
    @Override
    public List<Order> findOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    /**
     * Retrieves all orders placed by customers with the specified last name.
     *
     * @param lastName the last name of the customer
     * @return a list of matching orders
     */
    @Override
    public List<Order> findByLastName(String lastName) {
        return orderRepository.findByLastName(lastName);
    }

    /**
     * Retrieves all orders placed by customers with the specified first name.
     *
     * @param firstName the first name of the customer
     * @return a list of matching orders
     */
    @Override
    public List<Order> findByFirstName(String firstName) {
        return orderRepository.findByFirstName(firstName);
    }

    /**
     * Retrieves all orders placed using a specific email address.
     *
     * @param email the email address used in the order
     * @return a list of orders associated with the email
     */
    @Override
    public List<Order> findByEmail(String email) {
        return orderRepository.findByEmail(email);
    }

    /**
     * Retrieves all orders placed by customers with the specified full name.
     *
     * @param firstName the first name of the customer
     * @param lastName  the last name of the customer
     * @return a list of matching orders
     */
    @Override
    public List<Order> findByFullName(String firstName, String lastName) {
        return orderRepository.findByFullName(firstName, lastName);
    }
}
