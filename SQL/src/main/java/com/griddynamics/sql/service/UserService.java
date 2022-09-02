package com.griddynamics.sql.service;

import com.griddynamics.sql.base.User;

import java.sql.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserService implements Service<User, String> {

    private final Map<String, User> cache = new ConcurrentHashMap<>();

    private final Connection connection;

    public UserService(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public User find(final String id) {
        final User user = cache.get(id);
        if (user != null) {
            return user;
        }
        try {
            try (final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users;")) {
                final ResultSet result = preparedStatement.executeQuery();
                final String username = result.getString(1);
                final User found = new User(id, username);
                cache.put(id, found);
                return found;
            }
        }
        catch (SQLException e) {
            throw new IllegalStateException("Could not find user with id" + id, e);
        }
    }

    @Override
    public void delete(final String id) {
        try {
            try (final PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?;")) {
                preparedStatement.setString(1, id);
                final boolean result = preparedStatement.execute();
                preparedStatement.close();
                if (result) {
                    cache.remove(id);
                }
            }
        }
        catch (SQLException e) {
            throw new IllegalStateException("Could not delete user with id" + id, e);
        }
    }

    public void deleteThroughStatement(final String id) {
        try {
            try (final Statement statement = connection.createStatement()) {
                final boolean result = statement.execute("DELETE FROM users WHERE id=" + id + ";");
                if (result) {
                    cache.remove(id);
                }
            }
        }
        catch (SQLException e) {
            throw new IllegalStateException("Could not delete user with id" + id, e);
        }
    }

    @Override
    public void save(final User user) {
        try {
            try (final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (id,username) VALUES(?,?);")) {
                ;
                preparedStatement.setString(1, user.getId());
                preparedStatement.setString(2, user.getUsername());
                preparedStatement.execute();
                cache.put(user.getId(), user);
            }
        }
        catch (SQLException e) {
            throw new IllegalStateException("Could not save " + user, e);
        }
    }

    @Override
    public void update(final String id, final User user) {
        try {
            try (final PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users SET id = " + user.getId() + ",username = " + user.getUsername() + " WHERE id = " + id + ";")) {
                ;
                preparedStatement.execute();
                cache.remove(id);
                cache.put(user.getId(), user);
            }
        }
        catch (SQLException e) {
            throw new IllegalStateException("Could not update " + user, e);
        }
    }

}
