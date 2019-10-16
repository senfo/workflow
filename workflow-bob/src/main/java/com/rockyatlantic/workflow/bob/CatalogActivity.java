package com.rockyatlantic.workflow.bob;

import com.rockyatlatnic.workflow.manager.Activity;
import com.rockyatlatnic.workflow.manager.WorkflowContext;
import com.rockyatlatnic.workflow.manager.WorkflowState;
import com.rockyatlantic.workflow.models.Catalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements {@link Activity} to provide functionality for cataloging
 */
public class CatalogActivity implements Activity<Catalog> {
    private Catalog catalog;
    private final Logger LOG = LoggerFactory.getLogger(Activity.class);

    /**
     * Initializes a new instance of the {@link CatalogActivity} class
     * @param catalog Specifies the {@link Catalog} payload for the {@link Activity}
     */
    public CatalogActivity(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public Activity run(WorkflowContext context) {
        LOG.debug("run();");
        context.setWorkflowState(WorkflowState.Started);

        return null;

    }

    @Override
    public Catalog getPayload() {
        return this.catalog;
    }
}
