package vn.codegym.customerprovincemanagement.model;

import javax.persistence.*;

/**
 * Represents a Province entity in the system.
 * This entity is mapped to the 'province' table in the database.
 */
@Entity
@Table(name = "province")
public class Province {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    /**
     * Default constructor for Province.
     */
    public Province() {
    }

    /**
     * Returns the ID of the province.
     * @return The province's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the province.
     * @param id The ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the name of the province.
     * @return The province's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the province.
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
}
