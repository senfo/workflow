package com.rockyatlantic.workflow.rest.service;

import com.rockyatlantic.dao.WorkflowDao;
import com.rockyatlantic.workflow.models.Workflow;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Contains methods for abstracting {@link Workflow} functionality
 */
public class WorkflowService {
    private final Jdbi jdbi;
    private final static Logger LOG = LoggerFactory.getLogger(WorkflowService.class);

    /**
     * Initializes a new instance of the {@link WorkflowService} class
     * @param jdbi The primary {@link Jdbi} object from which to query the database through
     */
    public WorkflowService(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    /**
     * Creates associated table in the database
     */
    public void createTable() {
        LOG.warn("Database tables are typically created through Dropwizard migrations.");

        jdbi.withExtension(WorkflowDao.class, dao -> {
            dao.createTable();

            return null;
        });
    }

    /**
     * Gets a {@link Workflow} object by its unique identifier
     * @param uuid The unique identifier for the object for which to return
     * @return The model object found by the query, or null if it doesn't exist
     */
    public Workflow getWorkflowById(UUID uuid) {
        return jdbi.withExtension(WorkflowDao.class, dao -> dao.getById(uuid));
    }
}
