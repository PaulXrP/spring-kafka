package com.dev.pranay.Kafka_consumer.service;

import com.dev.pranay.Kafka_consumer.models.Product;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    // Listener for String messages
    @KafkaListener(topics = "test", groupId = "my-group2",
    containerFactory = "stringKafkaListenerContainerFactory")
    public void listenToMessages(@Payload String message,
                                 @Header(KafkaHeaders.OFFSET) Long offset)  {
        System.out.println("Consumed message: " + message + " at offset: " + offset);
    }

    // Listener for Product objects
    @KafkaListener(topics = "test2", groupId = "my-group2",
            containerFactory = "productKafkaListenerContainerFactory")
    public void listenToProduct(@Payload Product product,
                                @Header(KafkaHeaders.OFFSET) Long offset) {
        System.out.println("Consumed Product: " + product + " at offset: " + offset);
    }

    @KafkaListener(topics = "csv-topic", groupId = "my-group2",
            containerFactory = "stringKafkaListenerContainerFactory")
    public void listenCsv(@Payload String message,
                          @Header(KafkaHeaders.OFFSET) Long offset) {
        System.out.println("Received CSV Line: " + message);

        // Parsing CSV fields (Optional)
        String[] fields = message.split(",");
        System.out.println("Parsed fields: ");
        for(String field : fields) {
            System.out.println(field.trim());
        }
    }

    @KafkaListener(topics = "csv-topic", groupId = "my-group2",
    containerFactory = "stringKafkaListenerContainerFactory")
    public void consumeCsvContent(@Payload String message,
                                  @Header(KafkaHeaders.OFFSET) Long offset) {
        log.info("Received entire CSV content!");

        // Now message contains the whole CSV file content as one String
        // Let's split it into lines (rows)

        String[] lines = message.split("\\r?\\n"); // handles both Windows (\r\n) and Unix (\n) line endings

        for (String line : lines) {
            // Each line is a CSV record, we can process it here
             log.info("Processing line: {}", line);
        }
    }
}
