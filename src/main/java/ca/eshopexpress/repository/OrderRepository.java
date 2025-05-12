package ca.eshopexpress.repository;

import ca.eshopexpress.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link Order} entities.
 * <p>
 * Provides CRUD operations and custom query methods for retrieving orders based
 * on customer information such as first name, last name, email, or customer ID.
 * </p>
 *
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-05-11
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Retrieves a list of orders placed by a specific customer based on their customer ID.
     *
     * @param customerId the ID of the customer
     * @return a list of orders belonging to the customer
     */
    List<Order> findByCustomerId(int customerId);

    /**
     * Retrieves a list of orders placed by customers with the specified last name.
     *
     * @param lastName the last name of the customer
     * @return a list of orders with the matching last name
     */
    List<Order> findByLastName(String lastName);

    /**
     * Retrieves a list of orders placed by customers with the specified first name.
     *
     * @param firstName the first name of the customer
     * @return a list of orders with the matching first name
     */
    List<Order> findByFirstName(String firstName);

    /**
     * Retrieves a list of orders placed by customers with the specified email address.
     *
     * @param email the email address of the customer
     * @return a list of orders with the matching email
     */
    List<Order> findByEmail(String email);

    /**
     * Retrieves a list of orders placed by customers with a given first and last name.
     * This custom query is used when searching for orders based on full name.
     *
     * @param firstName the first name of the customer
     * @param lastName the last name of the customer
     * @return a list of orders placed by customers with the matching names
     */
    @Query("SELECT o FROM Order o WHERE o.firstName = :firstName AND o.lastName = :lastName")
    List<Order> findByFullName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
