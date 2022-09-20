package com.griddynamics.sqlutility.service;

import com.griddynamics.sqlutility.base.Database;
import com.griddynamics.sqlutility.model.Actor;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ActorService implements Service<Actor, Integer> {

    private final Database database;

    private final Map<Integer, Actor> cache = new ConcurrentHashMap<>();

    public ActorService(final Database database) {
        this.database = database;
    }

    @Override
    public Optional<Actor> findById(final Integer id) {
        final Actor actor = cache.get(id);
        if (actor != null) {
            return Optional.of(actor);
        }
        return database.findOne("SELECT fullName from actors WHERE actorId = ?;", (resultSet -> {
            try {
                final String fullName = resultSet.getString(1);
                return new Actor(id, fullName);
            }
            catch (SQLException e) {
                throw new RuntimeException("the columnIndex is not valid is or this method is called on a closed result set");
            }
        }), id);
    }

    @Override
    public Optional<Actor> save(final Actor type) {
        database.execute("INSERT INTO actors VALUES(?,?);", type.id(), type.fullName());
        cache.put(type.id(), type);
        return Optional.of(type);
    }

    @Override
    public List<Actor> findAll() {
        return database.findMany("SELECT * FROM actors;", (resultSet -> {
            final int actorId;
            try {
                actorId = resultSet.getInt(1);
                final String fullName = resultSet.getString(2);
                return new Actor(actorId, fullName);
            }
            catch (SQLException e) {
                throw new RuntimeException("the columnIndex is not valid is or this method is called on a closed result set");
            }
        }));
    }

}
