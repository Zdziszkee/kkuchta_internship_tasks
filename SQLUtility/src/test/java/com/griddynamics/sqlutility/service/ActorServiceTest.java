package com.griddynamics.sqlutility.service;

import com.griddynamics.sqlutility.base.Database;
import com.griddynamics.sqlutility.model.Actor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class ActorServiceTest {

    private final static String EXPECTED_NAME = "expectedName";

    private final static int EXPECTED_ID = 1;

    private final static List<Integer> EXPECTED_MOVIES = List.of(1, 2, 3);

    private final static Actor EXPECTED_ACTOR = new Actor(EXPECTED_NAME, EXPECTED_ID, EXPECTED_MOVIES);

    final Database mock = mock(Database.class);

    private ActorService actorService;

    @BeforeEach
    void setUp() {
        this.actorService = new ActorService(mock);
    }

    @Test
    void findById() {
        given(mock.findOne(eq("SELECT * FROM actors WHERE actorId = ?;"), any(Function.class), eq(EXPECTED_ID))).willReturn(
                Optional.of(EXPECTED_ACTOR));
        given(mock.findMany(eq("SELECT movieId FROM movie_actors JOIN movies ON movies.movieId = movie_actors.movieId WHERE actorId = ?;"),
                any(Function.class), eq(EXPECTED_ID))).willReturn(EXPECTED_MOVIES);
        final Optional<Actor> optionalActor = actorService.findById(EXPECTED_ID);
        assertTrue(optionalActor.isPresent());
        assertEquals(optionalActor.get(), EXPECTED_ACTOR);
    }

    @Test
    void save() {
        final Optional<Actor> optionalActor = actorService.save(EXPECTED_ACTOR);
        EXPECTED_MOVIES.forEach(movieId -> then(mock).should().execute("INSERT INTO movie_actors VALUES(?,?)", EXPECTED_ID, movieId));
        then(mock).should().execute("INSERT INTO actors VALUES(?,?)", EXPECTED_ID, EXPECTED_NAME);
    }

    @Test
    void findAll() {
        given(mock.findMany(eq("SELECT * FROM actors"), any(Function.class))).willReturn(List.of(EXPECTED_ACTOR));
    }

}