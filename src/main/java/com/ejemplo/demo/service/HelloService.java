package com.ejemplo.demo.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String obtenerSaludo() {
        return "¡Hola desde el Service en Spring Boot con Java 17!";
    }
}
