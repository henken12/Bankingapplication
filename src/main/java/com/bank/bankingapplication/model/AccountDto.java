package com.bank.bankingapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AccountDto {
    private Long accountId;

    @NotNull( message= "Account number cannot be null")
    @Pattern(regexp = "\\d{10}", message = "Account number must be exactly 10 digits")
    private String accountNumber;
    @NotBlank(message = "Account type is required")
    private String accountType;
    @NotNull(message = "Balance cannot be null")
    @Min(value = 0, message = "Balance must be zero or greater")
    private Double balance;
    @NotBlank(message = "Status is required")
    @Pattern(regexp = "ACTIVE|INACTIVE|CLOSED", message = "Status must be ACTIVE, INACTIVE, or CLOSED")
    private String status;
    @Column(name="email", unique=true, nullable=false)
    private String email;


    public AccountDto(String accountType, Double balance, String status, Long customerId, String email, String accountNumber) {
        this.accountType = accountType;
        this.balance = balance;
        this.status = status;
        this.email=email;
        this.accountNumber = accountNumber;
    }
}
