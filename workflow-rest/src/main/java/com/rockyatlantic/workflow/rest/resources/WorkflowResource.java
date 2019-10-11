package com.rockyatlantic.workflow.rest.resources;

import com.rockyatlantic.workflow.bob.BobWorkflowInitializer;
import com.rockyatlantic.workflow.rest.models.CollectionDto;
import com.rockyatlantic.workflow.rest.models.Workflow;
import com.rockyatlantic.workflow.rest.service.WorkflowService;
import com.rockyatlatnic.workflow.manager.Activity;
import com.rockyatlatnic.workflow.manager.Initializer;
import com.rockyatlatnic.workflow.manager.WorkflowManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
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

    /**
     * Fairly useless method for testing, but needs to be deleted
     */
    @POST
    //@Consumes(MediaType.APPLICATION_JSON)
    public Response post() {
        Initializer workflowInitializer = new BobWorkflowInitializer();
        Activity initialActivity = workflowInitializer.initialize();
        WorkflowManager workflowManager = new WorkflowManager(initialActivity);

        // Hold onto your butts!
        workflowManager.run();

        return Response.created(UriBuilder.fromResource(WorkflowResource.class).build()).build();
    }
}