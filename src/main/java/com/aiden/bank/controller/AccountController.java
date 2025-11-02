package com.aiden.bank.controller;
import com.aiden.bank.model.Account;
import com.aiden.bank.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

    @Autowired
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "Crear cuenta")
    @PostMapping
    public ResponseEntity<Account> create(
            @RequestBody @Valid Account account) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.create(account));
    }

    @Operation(summary = "Obtener cuenta por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Account> obtain(
            @PathVariable(name="id") Long id){
       Account account= accountService.getById(id);
        return ResponseEntity.ok(account);
    }

    @Operation(summary = "Actualizar cuenta por ID")
    @PutMapping("/{id}")
    public ResponseEntity<Account> update(
            @PathVariable(name="id")Long id,
            @RequestBody Account account){
        return ResponseEntity.ok(account);
    }

    @Operation(summary = "Eliminar cuenta por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(
            @PathVariable(name="id")Long id){
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
