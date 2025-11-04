package com.aiden.bank.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "simulation-events", groupId = "aidenbank-group")
    public void listen(String message) {
        System.out.println("📩 Mensaje recibido desde Kafka: " + message);
    }
}

