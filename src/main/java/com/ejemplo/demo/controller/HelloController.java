package com.ejemplo.demo.controller;

import com.ejemplo.demo.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hola")
    public String holaMundo() {
        return helloService.obtenerSaludo();
    }
}
