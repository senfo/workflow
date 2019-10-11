package com.rockyatlantic.workflow.rest.service;

import com.rockyatlantic.workflow.rest.models.Workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Contains methods for abstracting {@link Workflow} functionality
 */
public class WorkflowService {
    /**
     * Gets a {@link List} of all {@link Workflow} objects in the system
     * @return A {@link List} of all {@link Workflow} objects in the system
     */
    public List<Workflow> getWorkflows() {
        Workflow workflow = new Workflow(UUID.randomUUID());
        List<Workflow> workflows = new ArrayList<>();

        workflow.setName("Frank 'n Beans");
        workflow.setState(Workflow.State.Started);
        workflows.add(workflow);

        return workflows;
    }
}
