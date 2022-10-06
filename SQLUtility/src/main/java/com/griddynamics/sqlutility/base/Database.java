package com.griddynamics.sqlutility.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Database {

    private final Connection connection;

    public Database(final Connection connection) {
        this.connection = connection;
    }

    public void execute(String query, Object... args) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException("Something went wrong executing query!", e);
        }
    }

    public <R> Optional<R> findOne(String query, Function<ResultSet, R> mapper, Object... args) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            final ResultSet result = preparedStatement.executeQuery();
            if (!result.next()) {
                return Optional.empty();
            }
            if (!result.isLast()) {
                throw new MultipleResultsException("Found multiple results satisfying query!");
            }
            return Optional.of(mapper.apply(result));
        }
        catch (SQLException e) {
            throw new RuntimeException("Something went wrong executing query!", e);
        }
    }

    public <R> List<R> findMany(String query, Function<ResultSet, R> mapper, Object... args) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            final ResultSet result = preparedStatement.executeQuery();
            final List<R> results = new ArrayList<>();
            while (result.next()) {
                results.add(mapper.apply(result));
            }
            return Collections.unmodifiableList(results);
        }
        catch (SQLException e) {
            throw new RuntimeException("Something went wrong executing query!", e);
        }
    }

}
