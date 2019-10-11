package com.rockyatlatnic.workflow.manager;

/**
 * Defines the contract for a class that run an activity
 */
public interface Activity {
    /**
     * Runs an activity
     * @param context The {@link WorkflowContext} object containing the information about the workflow in its context
     * @return A reference to an {@link Activity} object containing instructions to run, or null if workflow is complete
     */
    Activity run(WorkflowContext context);
}
