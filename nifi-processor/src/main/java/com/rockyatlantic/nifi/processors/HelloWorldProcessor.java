package com.rockyatlantic.nifi.processors;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.nifi.components.PropertyDescriptor;
import org.apache.nifi.components.ValidationResult;
import org.apache.nifi.flowfile.FlowFile;
import org.apache.nifi.processor.AbstractProcessor;
import org.apache.nifi.processor.ProcessContext;
import org.apache.nifi.processor.ProcessSession;
import org.apache.nifi.processor.Relationship;
import org.apache.nifi.processor.exception.ProcessException;
import org.apache.nifi.util.StopWatch;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloWorldProcessor extends AbstractProcessor {
    private Set<Relationship> relationships = new HashSet<>();
    private List<PropertyDescriptor> propertyDescriptors = new ArrayList<>();
    private static final String TEMPLATE_DESCRIPTOR = "Template";
    private static final String DEFAULT_TEMPLATE_VALUE = "Hello from %s!";
    private static PropertyDescriptor TEMPLATE = new PropertyDescriptor.Builder()
            .name(TEMPLATE_DESCRIPTOR)
            .description("Provides a simple template for modifying the log output. Must contain exactly one %s")
            .defaultValue(DEFAULT_TEMPLATE_VALUE)
            .addValidator((subject, input, context) -> {
                Pattern pattern = Pattern.compile("%s");
                Matcher matcher = pattern.matcher(input);
                int count = 0;

                while (matcher.find()) {
                    count++;
                }

                return new ValidationResult.Builder()
                        .subject(subject)
                        .input(input)
                        .valid(count == 1)
                        .build();
            })
            .build();

    private static final Relationship SUCCESS = new Relationship.Builder().name("success").description("Files are routed to success").build();
    private static final Relationship ERROR = new Relationship.Builder().name("error").description("Files are routed to error").build();

    /**
     * Initializes a new instance of the {@link HelloWorldProcessor} class
     */
    public HelloWorldProcessor() {
        this.propertyDescriptors.add(TEMPLATE);
        this.relationships.add(SUCCESS);
        this.relationships.add(ERROR);
    }

    @Override
    public Set<Relationship> getRelationships() {
        return this.relationships;
    }

    @Override
    protected List<PropertyDescriptor> getSupportedPropertyDescriptors() {
        return this.propertyDescriptors;
    }

    @Override
    public void onTrigger(ProcessContext context, ProcessSession session) throws ProcessException {
        final FlowFile flowFile = session.get();
        final StopWatch stopWatch = new StopWatch(true);

        if (flowFile == null) {
            return;
        }

        FlowFile transferFile = session.create(flowFile);

        try (InputStream inputStream = session.read(flowFile)) {
            String jsonContent = IOUtils.toString(inputStream, Charset.defaultCharset());
            String templateValue = context.getProperty(TEMPLATE_DESCRIPTOR).getValue();
            SampleTemplate template = new Gson().fromJson(jsonContent, SampleTemplate.class);

            transferFile = session.putAttribute(transferFile, "id", Integer.toString(template.getId()));
            transferFile = session.putAttribute(transferFile, "name", template.getName());
            transferFile = session.putAttribute(transferFile, "description", template.getDescription());
            transferFile = session.putAttribute(transferFile, "original", jsonContent);

            this.getLogger().info(String.format(templateValue, template.getName()));

            session.getProvenanceReporter().modifyContent(transferFile, stopWatch.getElapsed(TimeUnit.MILLISECONDS));
            session.transfer(transferFile, SUCCESS);
        }
        catch (IOException err) {
            this.getLogger().error("An error occurred while parsing the template", err);
            session.transfer(transferFile, ERROR);
        }

        session.remove(flowFile);
    }
}
