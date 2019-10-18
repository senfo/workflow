package com.rockyatlatnic.workflow.manager;

import java.util.UUID;

/**
 * Contains fields that represent various information about the context of the workflow
 */
public class WorkflowContext {
    private WorkflowState workflowState;
    private final UUID workflowId;

    /**
     * Initializes a new instance of the {@link WorkflowContext} class
     */
    public WorkflowContext() {
        this(UUID.randomUUID());
    }

    /**
     * Initializes a new instance of the {@link WorkflowContext} class
     * @param workflowId Specifies the unique identifier for the workflow instance
     */
    public WorkflowContext(UUID workflowId) {
        this.workflowId = workflowId;
    }

    public WorkflowState getWorkflowState() {
        return workflowState;
    }

    public void setWorkflowState(WorkflowState workflowState) {
        this.workflowState = workflowState;
    }

    public UUID getWorkflowId() {
        return workflowId;
    }
}
