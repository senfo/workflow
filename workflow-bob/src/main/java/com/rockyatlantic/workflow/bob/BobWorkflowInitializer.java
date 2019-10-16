package com.rockyatlantic.workflow.bob;

import com.rockyatlantic.workflow.models.Catalog;
import com.rockyatlatnic.workflow.manager.Activity;
import com.rockyatlatnic.workflow.manager.Initializer;

/**
 * Contains methods for initiating a workflow
 */
public class BobWorkflowInitializer implements Initializer<Catalog> {
    /**
     * Initializes the default {@link Activity} class to start the workflow process
     * @return The {@link Activity} class, which represents the start of the workflow process
     */
    public Activity initialize(Catalog payload) {
        return new CatalogActivity(payload);
    }
}
