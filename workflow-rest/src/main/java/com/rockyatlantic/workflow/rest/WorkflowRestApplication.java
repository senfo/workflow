package com.rockyatlantic.workflow.rest;

import com.rockyatlantic.workflow.rest.resources.CatalogResource;
import com.rockyatlantic.workflow.rest.resources.WorkflowResource;
import com.rockyatlantic.workflow.rest.service.CatalogService;
import com.rockyatlantic.workflow.rest.service.WorkflowService;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

public class WorkflowRestApplication extends Application<WorkflowRestConfiguration> {
    public static void main(String[] args) throws Exception {
        new WorkflowRestApplication().run(args);
    }

    @Override
    public String getName() {
        return "Workflow REST";
    }

    @Override
    public void initialize(Bootstrap<WorkflowRestConfiguration> bootstrap) {
        // Database migrations run with command line arguments "db migrate {path/to/database.yaml}"
        bootstrap.addBundle(new MigrationsBundle<WorkflowRestConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(WorkflowRestConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(WorkflowRestConfiguration configuration, Environment environment) {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        final CatalogService catalogService = new CatalogService(jdbi);
        final WorkflowService workflowService = new WorkflowService(jdbi);

        environment.jersey().register(new CatalogResource(catalogService));
        environment.jersey().register(new WorkflowResource(workflowService));
    }
}
