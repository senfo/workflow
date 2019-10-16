package com.rockyatlatnic.workflow.manager;

/**
 * Defines the contract for a class that initializes a workflow process
 */
public interface Initializer<T> {
    /**
     * Initializes the default {@link Activity} class to start the workflow process
     * @param initialPayload Specifies the initial payload to run the {@link Activity}
     * @return The {@link Activity} class, which represents the start of the workflow process
     */
    Activity initialize(T initialPayload);
}
