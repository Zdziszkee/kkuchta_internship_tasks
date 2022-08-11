package com.griddynamics.serialversion.base;

import java.io.Serial;
import java.io.Serializable;

public class Client implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String username;

    private final String password;

    private final Integer balance;

    public Client() {

        this.username = "deafultname";
        this.password = "DEFAULTPASSWORD";
        this.balance = 13;
    }

    public Client(String username, String password, Integer balance) {

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

    public Integer getBalance() {

        return balance;
    }

}
