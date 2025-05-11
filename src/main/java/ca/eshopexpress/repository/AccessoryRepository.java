package ca.eshopexpress.repository;

import ca.eshopexpress.model.entity.Accessory;
import ca.eshopexpress.model.entity.Platform;
import ca.eshopexpress.model.enums.AccessoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link Accessory} entities.
 * <p>
 * Provides methods to perform CRUD operations and custom queries for accessories
 * based on type, name, price, and platform compatibility.
 * </p>
 *
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-05-10
 */
@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, Long> {

    /**
     * Retrieves a list of accessories matching the specified accessory type,
     * ignoring case sensitivity.
     *
     * @param accessoryType the type of accessory to search for
     * @return a list of matching accessories
     */
    List<Accessory> findByAccessoryTypeContainingIgnoreCase(AccessoryType accessoryType);

    /**
     * Retrieves a list of accessories whose names contain the specified string,
     * ignoring case.
     *
     * @param name the name substring to search for
     * @return a list of matching accessories
     */
    List<Accessory> findByNameContainingIgnoreCase(String name);

    /**
     * Retrieves a list of accessories compatible with the specified platform.
     *
     * @param platform the platform to filter by
     * @return a list of accessories that support the given platform
     */
    List<Accessory> findByPlatformsContaining(Platform platform);

    /**
     * Retrieves a list of accessories whose price is less than or equal to the specified maximum price.
     *
     * @param maxPrice the maximum price value
     * @return a list of affordable accessories under the given price
     */
    @Query("SELECT a FROM Accessory a WHERE a.price <= :maxPrice")
    List<Accessory> findByPriceLessThan(@Param("maxPrice") double maxPrice);
}
