package com.griddynamics.sqlutility.service;

import com.griddynamics.sqlutility.base.Database;
import com.griddynamics.sqlutility.model.Movie;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MovieService implements Service<Movie, Integer> {

    private final Database database;

    private final Map<Integer, Movie> cache = new ConcurrentHashMap<>();

    public MovieService(final Database database) {
        this.database = database;
    }

    @Override
    public Optional<Movie> findById(final Integer id) {
        final Movie movie = cache.get(id);
        if (movie != null) {
            return Optional.of(movie);
        }
        database.findOne("SELECT * FROM movies WHERE movieId = ?;", (resultSet -> {
            try {
                final String name = resultSet.getString(2);
                final String released = resultSet.getString(3);
                final long income = resultSet.getLong(4);
                return new Movie(id, LocalDate.parse(released), name, income);
            }
            catch (SQLException e) {
                throw new RuntimeException("the columnIndex is not valid is or this method is called on a closed result set");
            }
        }), id);
        return Optional.empty();
    }

    @Override
    public Optional<Movie> save(final Movie type) {
        database.execute("INSERT INTO movies VALUES(?,?,?,?);", type.id(), type.name(), type.released(), type.income());
        cache.put(type.id(), type);
        return Optional.of(type);
    }

    @Override
    public List<Movie> findAll() {
        return database.findMany("SELECT * FROM movies;", (resultSet -> {
            try {
                final int id = resultSet.getInt(1);
                final String name = resultSet.getString(2);
                final String released = resultSet.getString(3);
                final long income = resultSet.getLong(4);
                return new Movie(id, LocalDate.parse(released), name, income);
            }
            catch (SQLException e) {
                throw new RuntimeException("the columnIndex is not valid is or this method is called on a closed result set");
            }
        }));
    }

}
