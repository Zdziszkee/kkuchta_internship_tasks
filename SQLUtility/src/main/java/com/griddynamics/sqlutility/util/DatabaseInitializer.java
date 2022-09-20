package com.griddynamics.sqlutility.util;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class DatabaseInitializer {

    public static SQLiteDataSource init(String username, String password) {
        final Properties properties = new Properties();
        properties.setProperty("password", password);
        properties.setProperty("user", username);
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
        return dataSource;
    }

}
