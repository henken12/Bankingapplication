package com.bank.bankingapplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String accountType;
    private Double balance;
    private String status;


    public Account(String accountType, Double balance, String status) {
        this.accountType = accountType;
        this.balance = balance;
        this.status = status;
    }
}
