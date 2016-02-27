package com.baranovskiy.webapp.repository;

import com.baranovskiy.webapp.model.BaseModel;

import java.util.List;

/**
 * Defines C.R.U.D. operations
 *
 * @version 1.0
 * @author Baranovskiy Artem
 * @param <T> Object type of persistence
 */
public interface Operable<T extends BaseModel> {

    /**
     * Create new record
     *
     * @param t BaseEntity, that is going to be created
     */
    void add(T t);

    /**
     * Update record with specified identifier on another
     *
     * @param t Updated entity
     */
    void update(T t);

    /**
     * Delete entity from repository
     *
     * @param id Object identifier, which is going to be deleted
     */
    void delete(Integer id);

    /**
     * Find entity in the repository by its name
     *
     * @param name entity's name
     * @return Object, if it present on the repository or null
     */
    T findByName(String name);

    /**
     * Find entity in the repository by its identifier
     * @param id entity's id
     * @return Object, if it present on the repository or null
     */
    T findByID(Integer id);

    /**
     * Forms list of all objects in the repository
     *
     * @return List of objects in the repository
     */
    List<T> getAll();

    /**
     * Forms list of all objects in the repository sorted by name
     *
     * @return List of objects in the repository
     */
    List<T> getAllSortedByName();

    /**
     * Forms list of all objects in the repository sorted by id
     *
     * @return List of objects in the repository
     */
    List<T> getAllSortedById();

}
