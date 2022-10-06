package com.griddynamics.sql.model;

public class User {

    private final String id;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    private final String username;

    public User(final String id, final String username) {
        this.id = id;
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" + "id='" + id + '\'' + ", username='" + username + '\'' + '}';
    }

}
