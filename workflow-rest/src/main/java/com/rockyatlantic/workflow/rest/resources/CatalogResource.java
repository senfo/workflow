package com.rockyatlantic.workflow.rest.resources;

import com.rockyatlantic.workflow.bob.BobWorkflowInitializer;
import com.rockyatlantic.workflow.models.Catalog;
import com.rockyatlantic.workflow.models.Workflow;
import com.rockyatlantic.workflow.rest.models.CollectionDto;
import com.rockyatlantic.workflow.rest.service.CatalogService;
import com.rockyatlatnic.workflow.manager.Activity;
import com.rockyatlatnic.workflow.manager.Initializer;
import com.rockyatlatnic.workflow.manager.WorkflowManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.List;

@Path(CatalogResource.RESOURCE_PATH)
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CatalogResource {
    private final CatalogService catalogService;
    public static final String RESOURCE_PATH = "/catalog";

    /**
     * Initializes a new instance of the {@link CatalogResource} class
     * @param catalogService Specifies the primary {@link CatalogService} abstraction to query against
     */
    public CatalogResource(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    /**
     * Gets a {@link List} containing all {@link Catalog} objects in the system
     * @return A {@link List} containing all {@link Catalog}  objects in the system
     */
    @GET
    public CollectionDto<Catalog> index() {
        List<Catalog> catalogs = this.catalogService.getCatalogs();

        return new CollectionDto<>(catalogs);
    }

    /**
     * Takes a {@link Catalog} object and kicks off the workflow process
     * @param catalog The {@link Catalog} for which to initiate the workflow process
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(Catalog catalog) {
        Initializer<Catalog> workflowInitializer = new BobWorkflowInitializer();
        Activity initialActivity = workflowInitializer.initialize(catalog);
        WorkflowManager workflowManager = new WorkflowManager(initialActivity);

        // Hold onto your butts!
        workflowManager.run();

        return Response.created(UriBuilder.fromResource(CatalogResource.class).build()).entity(catalog).build();
    }
}