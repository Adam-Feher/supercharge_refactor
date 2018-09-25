package com.adamfeher.superbank.service;

import com.adamfeher.superbank.model.Account;
import com.adamfeher.superbank.model.Transaction;
import com.adamfeher.superbank.model.TransactionType;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions(Account account);

    List<Transaction> getTransactionsByDate(Account account, LocalDateTime from, LocalDateTime to);

    List<Transaction> getTransactionsByType(Account account, TransactionType type);

}
