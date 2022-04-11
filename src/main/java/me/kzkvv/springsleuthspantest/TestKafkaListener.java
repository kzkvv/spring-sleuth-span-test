package me.kzkvv.springsleuthspantest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class TestKafkaListener {
    private final Logger log = LoggerFactory.getLogger(TestKafkaListener.class);

    public static final String TEST_TOPIC = "test-topic";

    @KafkaListener(topics = TEST_TOPIC, groupId = "test-group")
    public void testListener(@Payload String message) {
        log.info("Message received, mdc: {}", MDC.getCopyOfContextMap());
    }

}
