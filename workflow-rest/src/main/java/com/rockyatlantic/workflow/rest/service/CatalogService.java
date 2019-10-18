package com.rockyatlantic.workflow.rest.service;

import com.rockyatlantic.dao.CatalogDao;
import com.rockyatlantic.workflow.models.Catalog;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Contains methods for abstracting {@link Catalog} functionality
 */
public class CatalogService {
    private final Jdbi jdbi;
    private final static Logger LOG = LoggerFactory.getLogger(CatalogService.class);

    /**
     * Initializes a new instance of the {@link CatalogService} class
     * @param jdbi The primary {@link Jdbi} object from which to query the database through
     */
    public CatalogService(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    /**
     * Creates associated table in the database
     */
    public void createTable() {
        LOG.warn("Database tables are typically created through Dropwizard migrations.");

        jdbi.withExtension(CatalogDao.class, dao -> {
            dao.createTable();

            return null;
        });
    }

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
