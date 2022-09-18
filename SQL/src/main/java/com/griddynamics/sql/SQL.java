package com.griddynamics.sql;

import com.griddynamics.sql.database.Database;
import com.griddynamics.sql.database.DefaultDatabase;
import com.griddynamics.sql.model.User;
import com.griddynamics.sql.service.UserService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL {

    public static void main(String[] args) {
        try {
            final Database database = new DefaultDatabase();
            final Connection connection = database.connect("username", "password");
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
