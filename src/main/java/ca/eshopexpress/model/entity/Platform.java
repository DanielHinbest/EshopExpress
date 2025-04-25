package ca.eshopexpress.model.entity;

import jakarta.persistence.*;

import java.util.Set;

/**
 * The class to represent the platforms available in the database
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-04-24
 */
@Entity
@Table(name = "platforms")
public class Platform {
    /**
     * The platform ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The platform name
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * The platform manufacturer
     */
    @Column
    private String manufacturer;

    /**
     * Return the platform ID
     * @return platform ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the platform ID
     * @param id platform ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Return the platform name
     * @return platform name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the platform name
     * @param name platform name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the platform manufacturer
     * @return platform manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Set the platform manufacturer
     * @param manufacturer the platform manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
