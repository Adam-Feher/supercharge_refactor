package com.adamfeher.superbank.service;

import com.adamfeher.superbank.exception.AccountServiceException;
import com.adamfeher.superbank.model.Account;
import com.adamfeher.superbank.model.TransactionType;
import com.adamfeher.superbank.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


class TransactionServiceImpTest {

    private Account account;
    private TransactionService ts;

    @Mock
    private User user;

    @BeforeEach
    void setUp() throws AccountServiceException {
        ts = new TransactionServiceImp();
        account = new Account(user);
        AccountService as = new AccountServiceImp();
        as.deposit(account, BigDecimal.valueOf(5000));
        as.deposit(account, BigDecimal.valueOf(1000));
        as.deposit(account, BigDecimal.valueOf(2000));
        as.withdraw(account, BigDecimal.valueOf(4000));
    }

    @Test
    void getAllTransactions() {
        assertEquals(4, ts.getAllTransactions(account).size());
    }

    @Test
    void getTransactionsByDate() {
        LocalDateTime to = LocalDateTime.now();
        LocalDateTime from = to.minusSeconds(10);
        assertEquals(4, ts.getTransactionsByDate(account, from, to).size());
    }

    @Test
    void getTransactionsByType() {
        assertEquals(3,ts.getTransactionsByType(account,TransactionType.DEPOSIT).size());
    }
}