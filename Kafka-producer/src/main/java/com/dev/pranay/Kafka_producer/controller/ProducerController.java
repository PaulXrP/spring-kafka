package com.dev.pranay.Kafka_producer.controller;

import com.dev.pranay.Kafka_producer.models.Product;
import com.dev.pranay.Kafka_producer.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam("message") String message) {
        String sentMessage = producerService.sendMessage(message);
        return new ResponseEntity<>(sentMessage, HttpStatus.OK);
    }

    @PostMapping("/publishProduct")
    public ResponseEntity<String> publish(@RequestBody Product product) {
        String sentProduct = producerService.sendProduct(product);
        return new ResponseEntity<>(sentProduct, HttpStatus.OK);
    }

    @PostMapping("/send-csv")
    public ResponseEntity<String> sendCsv(@RequestParam("filepath") String filePath) throws FileNotFoundException {
        String sentCsvFile = producerService.sendCsvFile(filePath);
        return new ResponseEntity<>(sentCsvFile, HttpStatus.OK);
    }

    @PostMapping("/send-full-csv")
    public ResponseEntity<String> sendFullCsv(@RequestParam String filepath) {
        String sentEntireCsvFile = producerService.sendEntireCsvFile(filepath);
        return new ResponseEntity<>(sentEntireCsvFile, HttpStatus.OK);
    }
}
