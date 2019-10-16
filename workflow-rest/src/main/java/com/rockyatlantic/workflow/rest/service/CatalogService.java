package com.rockyatlantic.workflow.rest.service;

import com.rockyatlantic.workflow.models.Catalog;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Contains methods for abstracting {@link Catalog} functionality
 */
public class CatalogService {
    /**
     * Gets a {@link List} of all {@link Catalog} objects in the system
     * @return A {@link List} of all {@link Catalog} objects in the system
     */
    public List<Catalog> getCatalogs() {
        Catalog catalog = new Catalog();
        List<Catalog> catalogs = new ArrayList<>();

        catalog.setCataLogId(UUID.randomUUID());
        catalogs.add(catalog);

        return catalogs;
    }
}
