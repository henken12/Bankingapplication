package com.bank.bankingapplication.controller;

import com.bank.bankingapplication.model.Account;
import com.bank.bankingapplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return ResponseEntity.ok(createdAccount);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountDetails(@PathVariable long id) {
        Optional<Account> accountDetails = accountService.getAccountDetails(id);
        if (accountDetails.isPresent()) {
            return ResponseEntity.ok(accountDetails.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable long id, @RequestBody Account accountDetails) {
        accountDetails.setAccountId(id);
        Account updatedAccount = accountService.updateAccount(accountDetails);
        return ResponseEntity.ok(updatedAccount);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable long id) {
        Optional<Account> accountDetails = accountService.getAccountDetails(id);
        if (accountDetails.isPresent()) {
            accountService.deleteAccount(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
