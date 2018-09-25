package com.adamfeher.superbank.service;

import com.adamfeher.superbank.exception.AccountServiceException;
import com.adamfeher.superbank.model.Account;
import com.adamfeher.superbank.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImpTest {

    private Account account;
    private Account account2;
    private AccountService accountService = new AccountServiceImp();

    @Mock
    private User user;
    @Mock
    private User user2;

    @BeforeEach
    void setUp() {
        this.account = new Account(user);
        this.account2 = new Account(user2);
    }

    @Test
    void ifAmountIsLessThanZeroThrowException() {
        assertThrows(AccountServiceException.class, () -> accountService.deposit(account, BigDecimal.valueOf(-1)));
    }

    @Test
    void ifAmountIsZeroThrowException() {
        assertThrows(AccountServiceException.class, () -> accountService.deposit(account, BigDecimal.ZERO));

    }

    @Test
    void ifAmountExceedAccountBalanceThrowException() {
        assertThrows(AccountServiceException.class, () -> accountService.withdraw(account, BigDecimal.valueOf(1)));
    }

    @Test
    void ifInvalidAmountWithdrawalThrowsException() throws AccountServiceException {
        accountService.deposit(account,BigDecimal.valueOf(1000));
        assertThrows(AccountServiceException.class, () -> accountService.withdraw(account,BigDecimal.valueOf(-1)));
    }

    @Test
    void ifSuccessfulDeposit() throws AccountServiceException {
        accountService.deposit(account, BigDecimal.valueOf(1000));
        assertEquals(BigDecimal.valueOf(1000), account.getBalance());
    }

    @Test
    void ifSuccessfulWithdrawal() throws AccountServiceException {
        accountService.deposit(account, BigDecimal.valueOf(1000));
        accountService.withdraw(account,BigDecimal.valueOf(500));
        assertEquals(BigDecimal.valueOf(500), account.getBalance());
    }

    @Test
    void ifTransferChangesSenderBalance() throws AccountServiceException {
        accountService.deposit(account, BigDecimal.valueOf(1000));
        accountService.transfer(account, account2, BigDecimal.valueOf(500));
        assertEquals(BigDecimal.valueOf(500), account.getBalance());
    }

    @Test
    void ifTransferChangesReceiverBalance() throws AccountServiceException {
        accountService.deposit(account, BigDecimal.valueOf(1000));
        accountService.transfer(account, account2, BigDecimal.valueOf(500));
        assertEquals(BigDecimal.valueOf(500), account2.getBalance());
    }
}