package com.adamfeher.superbank.service;

import com.adamfeher.superbank.exception.AccountServiceException;
import com.adamfeher.superbank.model.Account;

import java.math.BigDecimal;

public interface AccountService {
    void deposit(Account account, BigDecimal amount) throws AccountServiceException;

    void withdraw(Account account, BigDecimal amount) throws AccountServiceException;

    void transfer(Account senderAccount, Account receiverAccount, BigDecimal amount) throws AccountServiceException;
}
