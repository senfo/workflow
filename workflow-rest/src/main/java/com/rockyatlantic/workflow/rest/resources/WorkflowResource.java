package com.rockyatlantic.workflow.rest.resources;

import com.rockyatlantic.workflow.rest.models.CollectionDto;
import com.rockyatlantic.workflow.rest.models.Workflow;
import com.rockyatlantic.workflow.rest.service.WorkflowService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path(WorkflowResource.RESOURCE_PATH)
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class WorkflowResource {
    private final WorkflowService workflowService;
    public static final String RESOURCE_PATH = "/workflow";

    /**
     * Initializes a new instance of the {@link WorkflowService} class
     * @param workflowService Specifies the primary {@link WorkflowService} abstraction to query against
     */
    public WorkflowResource(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    /**
     * Gets a {@link List} containing all workflow objects in the system
     * @return A {@link List} containing all workflow objects in the system
     */
    @GET
    public CollectionDto<Workflow> index() {
        List<Workflow> workflows = this.workflowService.getWorkflows();

        return new CollectionDto<>(workflows);
    }
}