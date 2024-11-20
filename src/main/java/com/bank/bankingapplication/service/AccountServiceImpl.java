package com.bank.bankingapplication.service;

import com.bank.bankingapplication.model.Account;
import com.bank.bankingapplication.model.AccountDto;
import com.bank.bankingapplication.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(AccountDto accountDto) {
        // Check if an account with the same email already exists
        Optional<Account> existingAccount = Optional.ofNullable(accountRepository.findByAccountNumber(accountDto.getAccountNumber()));

        if (existingAccount.isPresent()) {
            // Logic for existing account: Throw an exception or handle it as needed
            throw new IllegalArgumentException("An account with this email already exists: " + accountDto.getAccountNumber());
        }

        Account newAccount = new Account();
        newAccount.setAccountNumber(accountDto.getAccountNumber());
        newAccount.setBalance(accountDto.getBalance());
        newAccount.setAccountType(accountDto.getAccountType());

        // Save the new account if it doesn't exist
        return accountRepository.save(newAccount);
    }

    @Override
    public Optional<Account> getAccountDetails(Long accountId) {
        return accountRepository.findById(accountId);
    }

    @Override
    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long accountId) {
     accountRepository.deleteById(accountId);
    }

    @Override
    public Account getAccountEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Account getAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
}
