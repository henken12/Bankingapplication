//package com.bank.bankingapplication;
//
//import com.bank.bankingapplication.controller.AccountController;
//import com.bank.bankingapplication.model.Account;
//import com.bank.bankingapplication.model.AccountDto;
//import com.bank.bankingapplication.model.response.ResponseData;
//import com.bank.bankingapplication.service.AccountService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(AccountController.class)
//class AccountControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private AccountService accountService;
//
//    @Test
//    void createAccount_Success() throws Exception {
//        // Arrange
//        AccountDto accountDto = new AccountDto();
//        accountDto.setAccountNumber("1234567890");
//        accountDto.setAccountType("SAVINGS");
//        accountDto.setBalance(1000.0);
//        accountDto.setStatus("ACTIVE");
//        accountDto.setEmail("test@example.com");
//
//        Account account = new Account();
//        account.setAccountId(1L);
//        account.setAccountNumber("1234567890");
//        account.setEmail("test@example.com");
//
//        when(accountService.createAccount(Mockito.any(AccountDto.class))).thenReturn(new ResponseData("0","Success"));
//
//        // Act & Assert
//        mockMvc.perform(post("/api/accounts")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("""
//                        {
//                            "accountNumber": "1234567890",
//                            "accountType": "SAVINGS",
//                            "balance": 1000.0,
//                            "status": "ACTIVE",
//                            "email": "test@example.com"
//                        }
//                        """))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.accountId").value(1))
//                .andExpect(jsonPath("$.email").value("test@example.com"));
//    }
//
//    @Test
//    void getAccountDetails_Success() throws Exception {
//        // Arrange
//        Account account = new Account();
//        account.setAccountId(1L);
//        account.setAccountNumber("1234567890");
//
//        when(accountService.getAccountDetails(1L)).thenReturn(Optional.of(account));
//
//        // Act & Assert
//        mockMvc.perform(get("/api/accounts/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.accountNumber").value("1234567890"));
//    }
//}
