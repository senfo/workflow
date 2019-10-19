package com.rockyatlantic.nifi.processors;

import org.apache.nifi.processor.AbstractProcessor;
import org.apache.nifi.processor.ProcessContext;
import org.apache.nifi.processor.ProcessSession;
import org.apache.nifi.processor.exception.ProcessException;

public class HelloWorldProcessor extends AbstractProcessor {
    @Override
    public void onTrigger(ProcessContext processContext, ProcessSession processSession) throws ProcessException {
        this.getLogger().info("Hello world!");
    }
}
