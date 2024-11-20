package com.bank.bankingapplication.service;

import com.bank.bankingapplication.model.Account;
import com.bank.bankingapplication.model.AccountDto;

import java.util.Optional;

public interface AccountService {
    public Account createAccount(AccountDto account);
    public Optional<Account> getAccountDetails(Long accountId);
    public Account updateAccount(Account account);
    public void deleteAccount(Long accountId);
    public Account getAccountEmail(String email);
    public Account getAccountNumber(String accountNumber);

}
