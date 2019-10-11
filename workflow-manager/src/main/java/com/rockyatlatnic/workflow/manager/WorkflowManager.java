package com.rockyatlatnic.workflow.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The primary workflow manager for the application
 */
public class WorkflowManager {
    private Activity currentActivity;
    private final WorkflowContext workflowContext = new WorkflowContext();
    private final Logger LOG = LoggerFactory.getLogger(WorkflowManager.class);

    /**
     * Initializes a new instance of the {@link WorkflowManager} class
     * @param initialActivity The initial {@link Activity} from which to start the workflow
     */
    public WorkflowManager(Activity initialActivity) {
        this.currentActivity = initialActivity;
    }

    /**
     * Runs the workflow through, to completion
     */
    public void run() {
        LOG.debug("run()");

        while (this.currentActivity != null) {
            this.currentActivity = this.currentActivity.run(this.workflowContext);
        }
    }

    /**
     * Starts the workflow in from its current state
     * @return The next {@link Activity} in the workflow
     */
    public Activity start() {
        return currentActivity.run(workflowContext);
    }

    public WorkflowContext getWorkflowContext() {
        return workflowContext;
    }
}
