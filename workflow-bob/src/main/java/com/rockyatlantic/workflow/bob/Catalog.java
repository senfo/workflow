package com.rockyatlantic.workflow.bob;

import com.rockyatlatnic.workflow.manager.Activity;
import com.rockyatlatnic.workflow.manager.WorkflowContext;
import com.rockyatlatnic.workflow.manager.WorkflowState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements {@link Activity} to provide functionality for cataloging
 */
public class Catalog implements Activity {
    private final Logger LOG = LoggerFactory.getLogger(Activity.class);

    @Override
    public Activity run(WorkflowContext context) {
        LOG.debug("run();");
        context.setWorkflowState(WorkflowState.Started);

        return null;
    }
}
