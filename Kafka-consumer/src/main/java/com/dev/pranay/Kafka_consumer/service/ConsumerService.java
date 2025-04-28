package com.dev.pranay.Kafka_consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "test", groupId = "my-group")
    public void listenToMessages(@Payload String message,
                                 @Header(KafkaHeaders.OFFSET) Long offset)  {
        System.out.println("Consumed message: " + message + " at offset: " + offset);
    }
}
