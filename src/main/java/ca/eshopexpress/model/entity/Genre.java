package ca.eshopexpress.model.entity;

import jakarta.persistence.*;

/**
 * The class to represent the different genres available
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-04-24
 */
@Entity
@Table(name = "genres")
public class Genre {
    /**
     * The genre's ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The genre's name
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Get the genre ID
     * @return genre ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the genre ID
     * @param id Genre ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the genre name
     * @return genre name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the genre name
     * @param name genre name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the genre string
     * @return genre string
     */
    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}