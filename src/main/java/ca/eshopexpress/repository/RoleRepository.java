package ca.eshopexpress.repository;

import ca.eshopexpress.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link Role} entities.
 * <p>
 * Provides CRUD operations and query methods for managing user roles.
 * </p>
 *
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-05-11
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Retrieves a role by its name.
     *
     * @param name the name of the role (e.g., 'USER', 'ADMIN')
     * @return the role matching the specified name
     */
    Role findByName(String name);

    /**
     * Retrieves a list of all roles in the system.
     *
     * @return a list of all available roles
     */
    List<Role> findAll();
}
