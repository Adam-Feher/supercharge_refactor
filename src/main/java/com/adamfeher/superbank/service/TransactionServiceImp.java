package com.adamfeher.superbank.service;

import com.adamfeher.superbank.model.Account;
import com.adamfeher.superbank.model.Transaction;
import com.adamfeher.superbank.model.TransactionType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionServiceImp implements TransactionService {

    @Override
    public List<Transaction> getAllTransactions(Account account) {
        return account.getTransactions();
    }

    @Override
    public List<Transaction> getTransactionsByDate(Account account, LocalDateTime from, LocalDateTime to) {
        return account.getTransactions().stream()
                .filter(transaction -> !transaction.getDate().isBefore(from) && !transaction.getDate().isAfter(to))
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getTransactionsByType(Account account, TransactionType type) {
        return account.getTransactions().stream()
                .filter(transaction -> type.equals(transaction.getType()))
                .collect(Collectors.toList());
    }
}
