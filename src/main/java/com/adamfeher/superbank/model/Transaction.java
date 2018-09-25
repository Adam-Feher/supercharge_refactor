package com.adamfeher.superbank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private UUID id;
    private LocalDateTime date;
    private BigDecimal amount;
    private BigDecimal currentBalance;
    private TransactionType type;

    public Transaction(BigDecimal amount, BigDecimal currentBalance, TransactionType type) {
        this.id = UUID.randomUUID();
        this.date = LocalDateTime.now();
        this.amount = amount;
        this.currentBalance = currentBalance;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public TransactionType getType() {
        return type;
    }
}
