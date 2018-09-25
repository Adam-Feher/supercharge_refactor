package com.adamfeher.superbank.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Account {
    private UUID id;
    private final User owner;
    private BigDecimal balance;
    private List<Transaction> transactions;

    public Account(User owner) {
        this.id = UUID.randomUUID();
        this.owner = owner;
        this.balance = BigDecimal.ZERO;
        this.transactions = new ArrayList<>();
    }

    public void update(BigDecimal amount, TransactionType type) {
        Transaction transaction = new Transaction(amount, this.getBalance(), type);

        if (transaction.getType() == TransactionType.DEPOSIT) {
            this.balance = this.balance.add(amount);
        } else {
            this.balance = this.balance.subtract(amount);
        }
        transactions.add(transaction);

    }

    public UUID getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", owner=" + owner +
                '}';
    }
}
