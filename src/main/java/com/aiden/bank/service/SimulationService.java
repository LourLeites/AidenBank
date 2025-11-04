package com.aiden.bank.service;

import com.aiden.bank.model.Simulation;
import com.aiden.bank.repository.ISimulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {

    private final ISimulationRepository simulationRepository;
    private final KafkaProducerService kafkaProducerService;

    public SimulationService(ISimulationRepository simulationRepository,
                             KafkaProducerService kafkaProducerService) {
        this.simulationRepository = simulationRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Async
    public void processSimulation(Long simulationId) {
        Simulation sim = simulationRepository.findById(simulationId)
                .orElseThrow(() -> new RuntimeException("Simulación no encontrada"));

        double r = sim.getInterestRate() / 12 / 100;
        int n = sim.getMonths();
        double P = sim.getAmount();
        double cuota = (P * r) / (1 - Math.pow(1 + r, -n));

        sim.setMonthlyPayment(cuota);
        simulationRepository.save(sim);

        System.out.println("Simulación completada: " + simulationId + " - Cuota: " + cuota);

        // 👉 Publicamos el evento
        kafkaProducerService.sendSimulationCompletedEvent(simulationId, cuota);
    }
}
