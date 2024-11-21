package com.bank.bankingapplication.model;


import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class AccountUpdateDto {
    private Long accountId;

    private String accountNumber;

    private Double balance;
    private String status;

    private String email;


    public AccountUpdateDto(Double balance, String status, String email, String accountNumber) {
        this.balance = balance;
        this.status = status;
        this.email=email;
        this.accountNumber = accountNumber;
    }
}
