package com.aiden.bank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Simulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;       // monto solicitado
    private int months;          // plazo en meses
    private double interestRate; // tasa de interés anual
    private double monthlyPayment; // resultado del cálculo
}

