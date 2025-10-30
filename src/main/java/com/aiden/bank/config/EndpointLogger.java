package com.aiden.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class EndpointLogger {

    public EndpointLogger(RequestMappingHandlerMapping mapping) {
        mapping.getHandlerMethods().forEach((key, value) ->
                System.out.println(key + " : " + value)
        );
    }
}

