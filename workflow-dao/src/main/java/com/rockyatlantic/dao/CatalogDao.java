package com.rockyatlantic.dao;

import com.rockyatlantic.workflow.models.Catalog;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.UUID;

/**
 * Defines the contract for a class that queries the database for {@link Catalog} data
 */
public interface CatalogDao extends BasicDao<Catalog> {
    @Override
    @SqlUpdate("CREATE TABLE catalog (catalogId uuid primary key)")
    void createTable();

    @SqlUpdate("INSERT INTO catalog (catalogId) VALUES (:catalogId)")
    void insert(@Bind("catalogId") UUID catalogId);

    @Override
    @SqlQuery("SELECT catalogId FROM catalog WHERE catalogId = :catalogId")
    Catalog getById(@Bind("catalogId") UUID catalogId);
}
