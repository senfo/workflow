package com.rockyatlantic.workflow.rest.resources;

import com.rockyatlantic.workflow.models.Catalog;
import com.rockyatlantic.workflow.rest.service.CatalogService;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * Ensures the {@link CatalogResource} class is functioning as expected
 */
public class CatalogResourceTest {
    @Test
    public void post() {
        CatalogResource catalogResource = new CatalogResource(new CatalogService(null));
        Catalog catalog = new Catalog();

        // TODO: We'll add more later
        catalog.setCataLogId(UUID.randomUUID());

        Response response = catalogResource.post(catalog);
        Response.StatusType statusType = response.getStatusInfo();

        assert statusType.getStatusCode() == 200 || statusType.getStatusCode() == 201;
        assert statusType.getReasonPhrase().equals("Created");
    }
}