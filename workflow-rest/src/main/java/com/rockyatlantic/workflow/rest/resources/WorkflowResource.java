package com.rockyatlantic.workflow.rest.resources;

import com.rockyatlantic.workflow.models.Workflow;
import com.rockyatlantic.workflow.rest.service.WorkflowService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path(WorkflowResource.RESOURCE_PATH)
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class WorkflowResource {
    public static final String RESOURCE_PATH = "/workflow";
    private final WorkflowService workflowService;

    /**
     * Initializes a new instance of the {@link WorkflowResource} class
     * @param workflowService Specifies the primary {@link WorkflowService} abstraction to query against
     */
    public WorkflowResource(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    /**
     * Gets a {@link Workflow} object from the service by its unique identifier
     * @param workflowId The unique identifier for which to query the service for
     * @return The {@link Workflow} object from the service, located by its unique identifier, or null if it doesn't exist
     */
    @GET
    @Path("/{workflowId}")
    public Workflow getById(@PathParam("workflowId") UUID workflowId) {
        return workflowService.getWorkflowById(workflowId);
    }
}
