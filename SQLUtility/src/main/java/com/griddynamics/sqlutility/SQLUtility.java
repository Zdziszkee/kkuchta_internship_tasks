package com.griddynamics.sqlutility;

import com.griddynamics.sqlutility.base.Database;
import com.griddynamics.sqlutility.util.DatabaseInitializer;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class SQLUtility {

    public static void main(String[] args) {
        final SQLiteDataSource dataSource = DatabaseInitializer.init("user", "password");
        try {
            try (final Connection connection = dataSource.getConnection()) {
                final Database database = new Database(connection);
                database.execute(
                        "create table if not exists movies(movieId int not null primary key, name varchar not null, released varchar" + " not " +
                                "null, " + "income bigint not null);");
                database.execute("create table if not exists actors( actorId int not null primary key, fullName varchar not null);");
                database.execute("delete from movies;");
                database.execute("delete from actors;");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
