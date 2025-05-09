package ca.eshopexpress.model.entity;

import jakarta.persistence.*;

/**
 * The class to represent the roles in the database
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-05-08
 */
@Entity
@Table(name = "roles")
public class Role {
    /**
     * The role ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The role name
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Get the role ID
     * @return role ID
     */
    public long getId() {
        return id;
    }

    /**
     * Set the role ID
     * @param id role ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the role name
     * @return role name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the role name
     * @param name role name
     */
    public void setName(String name) {
        this.name = name;
    }
}
