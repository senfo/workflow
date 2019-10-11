package com.rockyatlatnic.workflow.manager;

/**
 * Contains fields that represent various information about the context of the workflow
 */
public class WorkflowContext {
    private WorkflowState workflowState;

    public WorkflowState getWorkflowState() {
        return workflowState;
    }

    public void setWorkflowState(WorkflowState workflowState) {
        this.workflowState = workflowState;
    }
}
