package me.kzkvv.springsleuthspantest;

import brave.handler.MutableSpan;
import brave.handler.SpanHandler;
import brave.propagation.TraceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingConfiguration {
    private final Logger log = LoggerFactory.getLogger(TracingConfiguration.class);

    @Bean
    public SpanHandler debugSpanHandler() {
        return new SpanHandler() {
            @Override
            public boolean begin(TraceContext context, MutableSpan span, TraceContext parent) {
                log.info("New span. traceId: {}, spanId: {}, parentId: {}",
                        context.traceIdString(),
                        context.spanIdString(),
                        context.parentIdString()
                );
                return super.begin(context, span, parent);
            }
        };
    }
}
