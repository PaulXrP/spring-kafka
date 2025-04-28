package com.dev.pranay.Kafka_producer.controller;

import com.dev.pranay.Kafka_producer.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
