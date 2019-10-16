package com.rockyatlatnic.workflow.manager;

/**
 * Defines the contract for a class that run an activity
 * @param <T> Specifies the type of the payload
 */
public interface Activity<T> {
    /**
     * Runs an activity
     * @param context The {@link WorkflowContext} object containing the information about the workflow in its context
     * @return A reference to an {@link Activity} object containing instructions to run, or null if workflow is complete
     */
    Activity run(WorkflowContext context);

    /**
     * Gets a reference to the payload
     * @return A reference to the payload, or null if the activity doesn't require one
     */
    T getPayload();
}
