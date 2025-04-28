package com.dev.pranay.Kafka_producer.service;

import com.dev.pranay.Kafka_producer.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, Object> productKafkaTemplate;
    private static String TOPIC = "test2";

    public String sendMessage(String message) {
//        kafkaTemplate.send(TOPIC, message);
        kafkaTemplate.send("test", "string-key", message);  // Sending a string message
        return "Message sent successfully to Kafka topic!";
    }

    public String sendProduct(Product product) {
//        productKafkaTemplate.send(TOPIC, product);
        productKafkaTemplate.send(TOPIC, "product-key", product);  // Sending a Product object
        return "Product sent successfully to Kafka topic!";
    }

    public String sendCsvFile(String filePath) throws FileNotFoundException {
          try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
                String line;
                int lineNumber = 0;
                while ((line = br.readLine()) != null) {
                    kafkaTemplate.send("csv-topic", String.valueOf(lineNumber++), line);
                    System.out.println("Sent: " + line);
                }
          }  catch (IOException e) {
              e.printStackTrace();
              return "Failed to send CSV data!";
          }
        return "CSV file sent successfully!";
    }

    public String sendEntireCsvFile(String filepath) {
        try {
            // Read entire file content into a single String
              String content = new String(Files.readAllBytes(Paths.get(filepath)));

            // Send the entire content in one Kafka message
            kafkaTemplate.send("csv-topic", content);

            System.out.println("Sent entire CSV content as one message.");
            return "CSV file sent successfully as a single message!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to send CSV data!";
        }
    }
}
