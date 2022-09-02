package com.griddynamics.sql;

import com.griddynamics.sql.base.User;
import com.griddynamics.sql.service.UserService;

import java.sql.*;
import java.util.Properties;

public class SQL {

    public static void main(String[] args) {
        try {
            final Properties properties = new Properties();
            properties.setProperty("password", "password");
            properties.setProperty("user", "user");
            final Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/database", properties);
            try (final PreparedStatement preparedStatement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS users (id varchar UNIQUE NOT NULL, username varchar NOT NULL);")) {
                preparedStatement.execute();
            }
            try (final PreparedStatement clearStatement = connection.prepareStatement("DELETE FROM users")) {
                clearStatement.execute();
            }
            final UserService userService = new UserService(connection);
            for (int i = 0; i < 100; i++) {
                userService.save(new User("" + i, "user" + i));
            }
            userService.delete("42");
            // userService.delete("'1' OR 1=1");
            userService.deleteThroughStatement("'1' OR 1=1");
            final ResultSet result = connection.prepareStatement("SELECT * FROM users").executeQuery();
            int count = 0;
            while (result.next()) {
                count++;
            }
            System.out.println(count);
        }
        catch (SQLException e) {
            throw new IllegalStateException("Could not initialize the database!", e);
        }
    }

}
