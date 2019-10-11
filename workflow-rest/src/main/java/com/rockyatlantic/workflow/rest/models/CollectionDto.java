package com.rockyatlantic.workflow.rest.models;

import java.util.List;

/**
 * Used for transmitting a collection of items via REST endpoint
 * @param <T> Any model type that should be transmitted via the endpoint
 */
public class CollectionDto<T> {
    private final List<T> items;

    public CollectionDto(List<T> items) {
        this.items = items;
    }

    public int getCount() {
        return items.size();
    }

    public List<T> getItems() {
        return items;
    }
}