package com.aiden.bank.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "simulation-events";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendSimulationCompletedEvent(Long simulationId, double cuota) {
        String message = "Simulación " + simulationId + " completada con cuota " + cuota;
        kafkaTemplate.send(TOPIC, message);
        System.out.println("✅ Enviado a Kafka: " + message);
    }
}

