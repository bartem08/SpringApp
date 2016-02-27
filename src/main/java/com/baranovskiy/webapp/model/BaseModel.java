package com.baranovskiy.webapp.model;

import javax.persistence.*;

/**
 * Base model of entity, includes identifier and entity name
 *
 * @version 1.0
 * @author Baranovskiy Artem
 */
@MappedSuperclass
public abstract class BaseModel {

    /** Entity ID **/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    protected Integer ID;

    /** Entity name **/
    @Column(name = "name", length = 20, nullable = false)
    protected String name;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
