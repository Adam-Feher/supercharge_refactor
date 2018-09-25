package com.adamfeher.superbank.model;

import java.util.UUID;

public class User {
    private UUID id;
    private final String name;
    private Account account;

    public User(String name, Account account) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.account = account;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Account getAccount() {
        return account;
    }
}
