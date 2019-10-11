package com.rockyatlantic.workflow.rest;

import com.rockyatlantic.workflow.rest.resources.WorkflowResource;
import com.rockyatlantic.workflow.rest.service.WorkflowService;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class WorkflowRestApplication extends Application<WorkflowRestConfiguration> {
    public static void main(String[] args) throws Exception {
        new WorkflowRestApplication().run(args);
    }

    @Override
    public String getName() {
        return "Workflow REST";
    }

    @Override
    public void run(WorkflowRestConfiguration workflowRestConfiguration, Environment environment) {
        environment.jersey().register(new WorkflowResource(new WorkflowService()));
    }
}
