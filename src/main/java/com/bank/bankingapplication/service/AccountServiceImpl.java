package com.bank.bankingapplication.service;

import com.bank.bankingapplication.model.Account;
import com.bank.bankingapplication.model.AccountDto;
import com.bank.bankingapplication.model.AccountUpdateDto;
import com.bank.bankingapplication.model.response.ResponseData;
import com.bank.bankingapplication.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public ResponseData createAccount(AccountDto accountDto) {
        // Check if an account with the same email already exists
        Optional<Account> existingAccount = Optional.ofNullable(accountRepository.findByAccountNumber(accountDto.getAccountNumber()));

        if (existingAccount.isPresent()) {
            // Logic for existing account: Throw an exception or handle it as needed
            return new ResponseData("-1", "Account already exists " + accountDto.getAccountNumber());
        }

        Account newAccount = new Account();
        newAccount.setAccountNumber(accountDto.getAccountNumber());
        newAccount.setBalance(accountDto.getBalance());
        newAccount.setAccountType(accountDto.getAccountType());
        newAccount.setStatus(accountDto.getStatus());
        newAccount.setEmail(accountDto.getEmail());

        // Save the new account if it doesn't exist
         accountRepository.save(newAccount);
         return new ResponseData("1", "Account created", newAccount);
    }

    @Override
    public Optional<Account> getAccountDetails(Long accountId) {
        return accountRepository.findById(accountId);
    }

    @Override
    public Account updateAccount(AccountUpdateDto accountDto) {
        // Find the existing account by ID
        Optional<Account> existingAccountOpt = accountRepository.findById(accountDto.getAccountId());

        if (existingAccountOpt.isPresent()) {
            Account existingAccount = existingAccountOpt.get();

            // Update fields from DTO
            existingAccount.setAccountNumber(accountDto.getAccountNumber());
            existingAccount.setBalance(accountDto.getBalance());
            existingAccount.setStatus(accountDto.getStatus());
            existingAccount.setEmail(accountDto.getEmail());

            // Save the updated account
            return accountRepository.save(existingAccount);
        } else {
            throw new IllegalArgumentException("Account not found with ID: " + accountDto.getAccountId());
        }
    }


    public Account updateAccount(Account accountDto) {
        // Find the existing account by ID
        Optional<Account> existingAccountOpt = accountRepository.findById(accountDto.getAccountId());

        if (existingAccountOpt.isPresent()) {
            Account existingAccount = existingAccountOpt.get();

            // Update fields from DTO
            existingAccount.setAccountNumber(accountDto.getAccountNumber());
            existingAccount.setBalance(accountDto.getBalance());
            existingAccount.setStatus(accountDto.getStatus());
            existingAccount.setEmail(accountDto.getEmail());

            // Save the updated account
            return accountRepository.save(existingAccount);
        } else {
            throw new IllegalArgumentException("Account not found with ID: " + accountDto.getAccountId());
        }
    }

    @Override
    public void deleteAccount(Long accountId) {
        // Check if the account exists before attempting to delete
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            accountRepository.deleteById(accountId);
        } else {
            throw new IllegalArgumentException("Account with ID " + accountId + " does not exist.");
        }
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
