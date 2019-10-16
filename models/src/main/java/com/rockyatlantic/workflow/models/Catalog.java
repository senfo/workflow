package com.rockyatlantic.workflow.models;

import java.util.UUID;

/**
 * Contains fields that represent a catalog request
 */
public class Catalog {
    private UUID cataLogId;

    /**
     * Initializes a new instance of the {@link Catalog} class
     */
    public Catalog() {
    }

    public UUID getCataLogId() {
        return cataLogId;
    }

    public void setCataLogId(UUID cataLogId) {
        this.cataLogId = cataLogId;
    }
}
