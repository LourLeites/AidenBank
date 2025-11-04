package com.aiden.bank.controller;

import com.aiden.bank.model.Simulation;
import com.aiden.bank.repository.ISimulationRepository;
import com.aiden.bank.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/simulations")
public class SimulationController {

    @Autowired
    private SimulationService simulationService;
    @Autowired
    private ISimulationRepository simulationRepository;

    @PostMapping
    public ResponseEntity<Simulation> createSimulation(@RequestBody Simulation simulation) {
        Simulation saved = simulationRepository.save(simulation);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<String> startSimulation(@PathVariable(name = "id") Long id) {
        simulationService.processSimulation(id);
        return ResponseEntity.ok("Simulación iniciada en background");
    }
}

