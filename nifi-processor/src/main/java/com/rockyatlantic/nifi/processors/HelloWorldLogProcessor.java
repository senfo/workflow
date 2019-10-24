package com.rockyatlantic.nifi.processors;

import org.apache.nifi.flowfile.FlowFile;
import org.apache.nifi.processor.AbstractProcessor;
import org.apache.nifi.processor.ProcessContext;
import org.apache.nifi.processor.ProcessSession;
import org.apache.nifi.processor.exception.ProcessException;

public class HelloWorldLogProcessor extends AbstractProcessor {
    @Override
    public void onTrigger(ProcessContext context, ProcessSession session) throws ProcessException {
        final FlowFile flowFile = session.get();

        if (flowFile == null) {
            return;
        }

        int id = Integer.parseInt(flowFile.getAttribute("id"));
        String name = flowFile.getAttribute("name");
        String description = flowFile.getAttribute("description");
        String originalJson = flowFile.getAttribute("original");

        this.getLogger().info(String.format("ID: %d", id));
        this.getLogger().info(String.format("Name: %s", name));
        this.getLogger().info(String.format("Description: %s", description));
        this.getLogger().info(String.format("Original: %s", originalJson));

        // Don't forget to clean up
        session.remove(flowFile);
    }
}
