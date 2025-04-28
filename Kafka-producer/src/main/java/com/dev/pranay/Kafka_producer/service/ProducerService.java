package com.dev.pranay.Kafka_producer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static String TOPIC = "test";

    public String sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
        return "Message sent successfully to Kafka topic!";
    }


}
