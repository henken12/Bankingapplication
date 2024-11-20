package com.bank.bankingapplication.service;

import com.bank.bankingapplication.model.Account;

import java.util.Optional;

public interface AccountService {
    public Account createAccount(Account account);
    public Optional<Account> getAccountDetails(Long accountId);
    public Account updateAccount(Account account);
    public void deleteAccount(Long accountId);
}
