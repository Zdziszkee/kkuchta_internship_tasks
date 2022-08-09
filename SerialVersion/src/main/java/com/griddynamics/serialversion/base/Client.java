package com.griddynamics.serialversion.base;

import java.io.Serial;
import java.io.Serializable;

public class Client implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String username;

    private final String password;

    private final Double balance;


    public Client(String username, String password, Double balance) {

        this.username = username;
        this.password = password;
        this.balance = balance;

    }

    public String getUsername() {

        return username;
    }

    public String getPassword() {

        return password;
    }

    public Double getBalance() {

        return balance;
    }

}
