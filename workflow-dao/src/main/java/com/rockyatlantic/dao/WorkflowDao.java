package com.rockyatlantic.dao;

import com.rockyatlantic.workflow.models.Workflow;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.UUID;

/**
 * Defines the contract for a class that queries the database for {@link Workflow} data
 */
public interface WorkflowDao extends BasicDao<Workflow> {
    @Override
    @SqlUpdate("CREATE TABLE workflow (workflowId uuid primary key, name varchar(255), state varchar(50))")
    void createTable();

    @SqlUpdate("INSERT INTO workflow (workflowId, name, state) VALUES (:workflowId, :name, :state)")
    void insert(@Bind("workflowId") UUID workflowId, @Bind("name") String name, @Bind("state") String state);

    @Override
    @SqlQuery("SELECT workflowId FROM workflow WHERE workflowId = :workflowId")
    Workflow getById(@Bind("workflowId") UUID workflowId);
}
