package com.griddynamics.serialversion.base;

import java.io.Serial;
import java.io.Serializable;

public class Client implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String name;

    private final String password;

    private final Integer balance;

    public Client(String name, String password, Integer balance) {

        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public String getName() {

        return name;
    }

    public String getPassword() {

        return password;
    }

    public Integer getBalance() {

        return balance;
    }

}
