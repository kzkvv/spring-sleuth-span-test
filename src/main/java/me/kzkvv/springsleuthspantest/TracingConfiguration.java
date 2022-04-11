package me.kzkvv.springsleuthspantest;

import brave.baggage.BaggageField;
import brave.baggage.BaggageFields;
import brave.baggage.CorrelationScopeConfig;
import brave.context.slf4j.MDCScopeDecorator;
import brave.handler.MutableSpan;
import brave.handler.SpanHandler;
import brave.propagation.CurrentTraceContext;
import brave.propagation.TraceContext;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingConfiguration {
    private final Logger log = LoggerFactory.getLogger(TracingConfiguration.class);

//    @Bean
//    public BaggageField requestIdField() {
//        return BaggageField.create("x-request-id");
//    }
//
//    @Bean
//    public CurrentTraceContext.ScopeDecorator mdcScopeDecorator() {
//        return MDCScopeDecorator.newBuilder()
//                .clear()
//                .add(CorrelationScopeConfig.SingleCorrelationField.newBuilder(requestIdField())
//                        .flushOnUpdate()
//                        .build())
//                .add(CorrelationScopeConfig.SingleCorrelationField.create(BaggageFields.PARENT_ID))
//                .build();
//    }

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
