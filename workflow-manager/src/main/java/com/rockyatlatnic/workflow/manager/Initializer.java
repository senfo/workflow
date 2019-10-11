package com.rockyatlatnic.workflow.manager;

/**
 * Defines the contract for a class that initializes a workflow process
 */
public interface Initializer {
    /**
     * Initializes the default {@link Activity} class to start the workflow process
     * @return The {@link Activity} class, which represents the start of the workflow process
     */
    Activity initialize();
}
