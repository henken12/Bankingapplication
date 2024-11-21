package com.bank.bankingapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@NoArgsConstructor
@Data
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    private String accountNumber;
    private String accountType;
    private Double balance;
    private String status;
    private String email;


    public Account(String accountType, Double balance, String status, Long customerId, String email,String accountNumber) {
        this.accountType = accountType;
        this.balance = balance;
        this.status = status;
        this.email=email;
        this.accountNumber = accountNumber;
    }
}
