package com.aiden.bank.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TransferRequest {
    private Long fromAccountId;
    private Long toAccountId;
    private double amount;
}
