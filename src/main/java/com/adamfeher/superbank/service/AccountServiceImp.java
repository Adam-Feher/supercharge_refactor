package com.adamfeher.superbank.service;

import com.adamfeher.superbank.exception.AccountServiceException;
import com.adamfeher.superbank.model.Account;
import com.adamfeher.superbank.model.TransactionType;

import java.math.BigDecimal;

public class AccountServiceImp implements AccountService {
    @Override
    public void deposit(Account account, BigDecimal amount) throws AccountServiceException {
        if (checkAmountIsZeroOrLessThanZero(amount)) {
            throw new AccountServiceException(account, amount, "Invalid amount of transaction. Amount can't be or lower than 0");
        }
        account.update(amount,TransactionType.DEPOSIT);
    }

    @Override
    public void withdraw(Account account, BigDecimal amount) throws AccountServiceException {
        if (checkAmountIsZeroOrLessThanZero(amount)) {
            throw new AccountServiceException(account, amount, "You can't withdraw negative amount");
        }
        if (checkBalanceExceedZero(account, amount)) {
            throw new AccountServiceException(account, amount, "You can't withdraw this amount because it exceed your balance");
        }
        account.update(amount,TransactionType.WITHDRAW);
    }

    @Override
    public void transfer(Account senderAccount, Account receiverAccount, BigDecimal amount) throws AccountServiceException {
        if (checkAmountIsZeroOrLessThanZero(amount) || checkBalanceExceedZero(senderAccount, amount) ||
                receiverAccount == null) {
            throw new AccountServiceException(senderAccount, receiverAccount, amount, "Invalid transfer");
        }
        senderAccount.update(amount,TransactionType.WITHDRAW);
        receiverAccount.update(amount,TransactionType.DEPOSIT);
    }

    private boolean checkAmountIsZeroOrLessThanZero(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) <= 0;
    }

    private boolean checkBalanceExceedZero(Account account, BigDecimal amount) {
        return account.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) < 0;
    }
}
