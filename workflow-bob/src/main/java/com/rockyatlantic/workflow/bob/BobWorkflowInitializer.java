package com.rockyatlantic.workflow.bob;

import com.rockyatlatnic.workflow.manager.Activity;
import com.rockyatlatnic.workflow.manager.Initializer;

/**
 * Contains methods for initiating a workflow
 */
public class BobWorkflowInitializer implements Initializer {
    /**
     * Initializes the default {@link Activity} class to start the workflow process
     * @return The {@link Activity} class, which represents the start of the workflow process
     */
    public Activity initialize() {
        return new Catalog();
    }
}
