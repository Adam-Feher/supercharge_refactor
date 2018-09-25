package com.adamfeher.superbank.exception;

import com.adamfeher.superbank.model.Account;

import java.math.BigDecimal;

public class AccountServiceException extends Exception {
    public AccountServiceException(Account account, BigDecimal amount, String msg) {
        super(msg + account.toString() + amount.toString());
    }

    public AccountServiceException(Account sender, Account receiver, BigDecimal amount, String msg) {
        super(msg + sender.toString() + receiver.toString() + amount);
    }
}
