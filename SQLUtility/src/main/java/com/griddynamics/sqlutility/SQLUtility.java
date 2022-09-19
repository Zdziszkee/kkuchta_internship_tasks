package com.griddynamics.sqlutility;

import com.griddynamics.sqlutility.base.Database;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class SQLUtility {

    public static void main(String[] args) {
        final Properties properties = new Properties();
        properties.setProperty("password", "password");
        properties.setProperty("user", "user");
        final SQLiteDataSource dataSource = new SQLiteDataSource(new SQLiteConfig(properties));
        final String workingDir = System.getProperty("user.dir");
        final String databasePath = workingDir + "/database.db";
        try {
            final Path path = Paths.get(databasePath);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        }
        catch (IOException e) {
            throw new IllegalStateException("Could not create or find sqlite database file!", e);
        }
        dataSource.setUrl("jdbc:sqlite:" + databasePath);
        try {
            try (final Connection connection = dataSource.getConnection()) {
                final Database database = new Database(connection);
                database.execute(
                        "create table if not exists movies(movieId int not null primary key, name varchar not null, released varchar" + " not " +
                                "null, " + "income bigint not null);");
                database.execute("create table if not exists actors( actorId int not null primary key, fullName varchar not null);");
                database.execute(
                        " create table if not exists movie_actors( actorId int not null, movieId int not null, foreign key (actorId) references " + "actors (actorId), foreign key (movieId) references movies (movieId), primary key (actorId,movieId));");
                database.execute("delete from movies;");
                database.execute("delete from actors;");
                database.execute("delete from movie_actors;");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
