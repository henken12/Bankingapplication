package com.bank.bankingapplication.repository;

import com.bank.bankingapplication.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByEmail(String email);

    Account findByAccountNumber(String accountNumber);
}
