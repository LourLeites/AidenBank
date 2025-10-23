package com.aiden.bank.controller;
import com.aiden.bank.model.Account;
import com.aiden.bank.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/accounts")
public class AccountController {

    @Autowired
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> crear(
            @RequestBody @Valid Account account) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountService.create(account));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Account> obtain(
            @PathVariable(name="id") Long id){
       Account account= accountService.obtain(id);
        return ResponseEntity.ok(account);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Account> update(
            @PathVariable(name="id")Long id,
            @RequestBody Account account){
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(
            @PathVariable(name="id")Long id){
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
