package me.kzkvv.springsleuthspantest;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static me.kzkvv.springsleuthspantest.TestKafkaListener.TEST_TOPIC;

@RestController
@RequestMapping("/test")
public class TestController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Producer<String, Object> producer;

    public TestController(KafkaTemplate<String, String> kafkaTemplate,
                          Producer<String, Object> producer) {
        this.kafkaTemplate = kafkaTemplate;
        this.producer = producer;
    }

    @GetMapping("/1")
    public String generateTestKafkaMessage() {
        producer.send(
                new ProducerRecord<>(
                        TEST_TOPIC,
                        UUID.randomUUID().toString(),
                        "{}"
                )
        );
        return "msg was sent using producer";
    }

    @GetMapping("/2")
    public String generateTestKafkaMessage2() {
        kafkaTemplate.send(
                TEST_TOPIC, "{}"
        );
        return "msg was sent using kafkaTemplate";
    }

}
