package com.griddynamics.sql.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DefaultDatabase implements Database {

    private Connection connection;

    @Override
    public Connection connect(String username, String password) {
        final Properties properties = new Properties();
        properties.setProperty("password", password);
        properties.setProperty("user", username);
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/database", properties);
            try (final PreparedStatement preparedStatement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS users (id varchar UNIQUE NOT NULL, username varchar NOT NULL);")) {
                preparedStatement.execute();
            }
            try (final PreparedStatement clearStatement = connection.prepareStatement("DELETE FROM users")) {
                clearStatement.execute();
            }
            return connection;
        }
        catch (SQLException e) {
            throw new IllegalStateException("Could not initialize the database!", e);
        }
    }

}
