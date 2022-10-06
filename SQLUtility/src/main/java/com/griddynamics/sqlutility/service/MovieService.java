package com.griddynamics.sqlutility.service;

import com.griddynamics.sqlutility.base.Database;
import com.griddynamics.sqlutility.data.Movie;

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
        final List<Integer> actorIds = database.findMany(
                "SELECT actorId FROM movie_actors JOIN actors ON actors.actorId = movie_actors.actorId WHERE movieId = ?", (resultSet -> {
                    try {
                        return resultSet.getInt(1);
                    }
                    catch (SQLException e) {
                        throw new RuntimeException("the columnIndex is not valid is or this method is called on a closed result set");
                    }
                }), id);
        database.findOne("SELECT * FROM movies WHERE movieId = ?", (resultSet -> {
            try {
                final String name = resultSet.getString(2);
                final String released = resultSet.getString(3);
                final long income = resultSet.getLong(4);
                return new Movie(id, LocalDate.parse(released), name, income, actorIds);
            }
            catch (SQLException e) {
                throw new RuntimeException("the columnIndex is not valid is or this method is called on a closed result set");
            }
        }), id);
        return Optional.empty();
    }

    @Override
    public Optional<Movie> save(final Movie type) {
        type.actorsIds().forEach(actorId -> {
            database.execute("INSERT INTO movie_actors VALUES(?,?)", actorId, type.id());
        });
        database.execute("INSERT INTO movies VALUES(?,?,?,?)", type.id(), type.name(), type.released(), type.income());
        return Optional.of(type);
    }

    @Override
    public List<Movie> findAll() {
        return database.findMany("SELECT * FROM movies", (resultSet -> {
            try {
                final int id = resultSet.getInt(1);
                final String name = resultSet.getString(2);
                final String released = resultSet.getString(3);
                final long income = resultSet.getLong(4);
                final List<Integer> actorIds = database.findMany(
                        "SELECT actorId FROM movie_actors JOIN actors ON actors.actorId = movie_actors.actorId WHERE movieId = ?", (resultSet2 -> {
                            try {
                                return resultSet2.getInt(1);
                            }
                            catch (SQLException e) {
                                throw new RuntimeException("the columnIndex is not valid is or this method is called on a closed result set");
                            }
                        }), id);
                return new Movie(id, LocalDate.parse(released), name, income, actorIds);
            }
            catch (SQLException e) {
                throw new RuntimeException("the columnIndex is not valid is or this method is called on a closed result set");
            }
        }));
    }

}
