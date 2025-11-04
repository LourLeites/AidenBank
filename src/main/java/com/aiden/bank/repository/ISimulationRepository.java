package com.aiden.bank.repository;

import com.aiden.bank.model.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISimulationRepository  extends JpaRepository<Simulation, Long> { }

