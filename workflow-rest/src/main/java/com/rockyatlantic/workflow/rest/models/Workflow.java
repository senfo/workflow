package com.rockyatlantic.workflow.rest.models;

import java.util.UUID;

/**
 * Contains fields that represent a workflow object
 */
public class Workflow {
    private UUID workflowId;
    private String name;
    private State state;

    /**
     * Initializes a new instance of the {@link Workflow} class
     */
    public Workflow() {
    }

    /**
     * Initializes a new instance of the {@link Workflow} class
     * @param workflowId The unique identifier for the workflow
     */
    public Workflow(UUID workflowId) {
        this.workflowId = workflowId;
    }

    public UUID getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(UUID workflowId) {
        this.workflowId = workflowId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public enum State {
        Started,
        Complete
    }
}
