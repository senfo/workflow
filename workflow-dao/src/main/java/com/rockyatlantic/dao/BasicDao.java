package com.rockyatlantic.dao;

import java.util.UUID;

/**
 * Defines the contract for a basic DAO
 * @param <T>
 */
public interface BasicDao<T> {
    /**
     * Creates the specified table
     */
    void createTable();

    /**
     * Gets the specified model by its unique identifier
     * @param uuid The unique identifier for the object for which to return
     * @return The model object found by the query, or null if it doesn't exist
     */
    T getById(UUID uuid);
}
