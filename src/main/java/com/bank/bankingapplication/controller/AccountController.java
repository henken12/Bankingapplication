package com.bank.bankingapplication.controller;

import com.bank.bankingapplication.model.Account;
import com.bank.bankingapplication.model.AccountDto;
import com.bank.bankingapplication.model.AccountUpdateDto;
import com.bank.bankingapplication.model.response.ResponseData;
import com.bank.bankingapplication.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<ResponseData> createAccount(@RequestBody @Valid AccountDto account) {
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getAccountDetails(@PathVariable long id) {
        Optional<Account> accountDetails = accountService.getAccountDetails(id);
        if (accountDetails.isPresent()) {
            return ResponseEntity.ok(new ResponseData("0", "Success", accountDetails.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData> updateAccount(@PathVariable long id, @RequestBody AccountUpdateDto accountDto) {
        accountDto.setAccountId(id);
        Account updatedAccount = accountService.updateAccount(accountDto);
        return ResponseEntity.ok(new ResponseData("0", "Success", updatedAccount));
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


    @GetMapping("/get-by-email")
    public ResponseEntity<ResponseData> getAccountByEmail(@RequestParam String email) {
        Account account = accountService.getAccountEmail(email);
        if (account != null) {
            return ResponseEntity.ok(new ResponseData("0", "Success", account));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get-by-accountNumber")
    public ResponseEntity<ResponseData> getAccountByNumber(@RequestParam String accountNumber) {
        Account account = accountService.getAccountNumber(accountNumber);
        if (account != null) {
            return ResponseEntity.ok(new ResponseData("0", "Success", account));
        }
        return ResponseEntity.notFound().build();
    }
}


