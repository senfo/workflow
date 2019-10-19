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

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloWorldProcessor extends AbstractProcessor {
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

    @Override
    public Set<Relationship> getRelationships() {
        Set<Relationship> relationships = new HashSet<Relationship>() {{
            add(SUCCESS);
            add(ERROR);
        }};

        return relationships;
    }

    @Override
    protected List<PropertyDescriptor> getSupportedPropertyDescriptors() {
        List<PropertyDescriptor> propertyDescriptors = new ArrayList<PropertyDescriptor>() {{
            add(TEMPLATE);
        }};

        return propertyDescriptors;
    }

    @Override
    public void onTrigger(ProcessContext context, ProcessSession session) {
        final FlowFile flowFile = session.get();

        if (flowFile == null) {
            return;
        }

        session.read(flowFile, inputStream -> {
            String jsonContent = IOUtils.toString(inputStream, Charset.defaultCharset());
            String templateValue = context.getProperty(TEMPLATE_DESCRIPTOR).getValue();
            SampleTemplate template = new Gson().fromJson(jsonContent, SampleTemplate.class);

            this.getLogger().info(String.format(templateValue, template.getName()));
        });

        session.transfer(flowFile, SUCCESS);
        session.commit();
    }
}
