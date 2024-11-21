package com.bank.bankingapplication.controller;

import com.bank.bankingapplication.model.Account;
import com.bank.bankingapplication.model.AccountDto;
import com.bank.bankingapplication.service.AccountService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Account> createAccount(@RequestBody @Valid AccountDto account) {
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
    public ResponseEntity<Account> updateAccount(@PathVariable long id, @RequestBody AccountDto accountDto) {

        // Set the account ID in the DTO
        accountDto.setAccountId(id);

        // Call the service layer to update the account
        Account updatedAccount = accountService.updateAccount(accountDto);
        return ResponseEntity.ok(updatedAccount);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable long id) {
        try {
            accountService.deleteAccount(id);
            return ResponseEntity.noContent().build(); // Return 204 No Content if successful
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if the account does not exist
        }
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<Account> getAccountByEmail(@PathVariable String email) {
        Account account = accountService.getAccountEmail(email);
        if (account != null) {
            return ResponseEntity.ok(account);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/accountNumber/{accountNumber}")
    public ResponseEntity<Account> getAccountByNumber(@PathVariable String accountNumber) {
        Account account = accountService.getAccountNumber(accountNumber);
        if (account != null) {
            return ResponseEntity.ok(account);
        }
        return ResponseEntity.notFound().build();
    }
}


