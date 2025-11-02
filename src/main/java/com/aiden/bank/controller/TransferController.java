package com.aiden.bank.controller;

import com.aiden.bank.dto.TransferRequest;
import com.aiden.bank.model.Transfer;
import com.aiden.bank.service.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/transfers")
public class TransferController {
    @Autowired
    private TransferService transferService;

    @Operation(summary = "Crear transferencia")
    @PostMapping
    public ResponseEntity<Transfer> create(@RequestBody TransferRequest request) {
        Transfer transfer = transferService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(transfer);
    }
}