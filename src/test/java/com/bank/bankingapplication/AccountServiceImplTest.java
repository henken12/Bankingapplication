package com.bank.bankingapplication;

import com.bank.bankingapplication.model.Account;
import com.bank.bankingapplication.model.AccountDto;
import com.bank.bankingapplication.repository.AccountRepository;
import com.bank.bankingapplication.service.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAccount_Success() {
        // Arrange
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNumber("1234567890");
        accountDto.setAccountType("SAVINGS");
        accountDto.setBalance(1000.0);
        accountDto.setStatus("ACTIVE");
        accountDto.setEmail("test@example.com");

        Account account = new Account();
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountType(accountDto.getAccountType());
        account.setBalance(accountDto.getBalance());
        account.setStatus(accountDto.getStatus());
        account.setEmail(accountDto.getEmail());

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        // Act
        Account createdAccount = accountService.createAccount(accountDto);

        // Assert
        assertNotNull(createdAccount);
        assertEquals(accountDto.getEmail(), createdAccount.getEmail());
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void getAccountDetails_Success() {
        // Arrange
        Account account = new Account();
        account.setAccountId(1L);
        account.setAccountNumber("1234567890");

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        // Act
        Optional<Account> result = accountService.getAccountDetails(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("1234567890", result.get().getAccountNumber());
        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    void updateAccount_Success() {
        // Arrange
        Account existingAccount = new Account();
        existingAccount.setAccountId(1L);
        existingAccount.setAccountNumber("1234567890");

        AccountDto accountDto = new AccountDto();
        accountDto.setAccountId(1L);
        accountDto.setAccountNumber("0987654321");
        accountDto.setBalance(2000.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(existingAccount));
        when(accountRepository.save(any(Account.class))).thenReturn(existingAccount);

        // Act
        Account updatedAccount = accountService.updateAccount(accountDto);

        // Assert
        assertNotNull(updatedAccount);
        assertEquals("0987654321", updatedAccount.getAccountNumber());
        verify(accountRepository, times(1)).save(existingAccount);
    }

    @Test
    void deleteAccount_Success() {
        // Arrange
        Account account = new Account();
        account.setAccountId(1L);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        doNothing().when(accountRepository).deleteById(1L);

        // Act
        accountService.deleteAccount(1L);

        // Assert
        verify(accountRepository, times(1)).deleteById(1L);
    }
}
