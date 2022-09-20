package com.griddynamics.sqlutility.service;

import com.griddynamics.sqlutility.base.Database;
import com.griddynamics.sqlutility.model.Actor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ActorServiceTest {

    private final static String EXPECTED_NAME = "expectedName";

    private final static int EXPECTED_ID = 1;

    private final static Actor EXPECTED_ACTOR = new Actor(EXPECTED_ID, EXPECTED_NAME);

    final Database mock = mock(Database.class);

    private ActorService actorService;

    @BeforeEach
    void setUp() {
        this.actorService = new ActorService(mock);
    }

    @Test
    void findById() {
        given(mock.findOne(eq("SELECT * FROM actors WHERE actorId = ?;"), any(), eq(EXPECTED_ID))).willReturn(Optional.of(EXPECTED_ACTOR));
        final Optional<Actor> optionalActor = actorService.findById(EXPECTED_ID);
        verify(mock).findOne(eq("SELECT * FROM actors WHERE actorId = ?;"), any(), eq(EXPECTED_ID));
        assertTrue(optionalActor.isPresent());
        assertEquals(optionalActor.get(), EXPECTED_ACTOR);
    }

    @Test
    void save() {
        final Optional<Actor> optionalActor = actorService.save(EXPECTED_ACTOR);
        then(mock).should().execute("INSERT INTO actors VALUES(?,?);", EXPECTED_ID, EXPECTED_NAME);
    }

    @Test
    void findAll() {
        given(mock.findMany(eq("SELECT * FROM actors;"), any())).willReturn(List.of(EXPECTED_ACTOR));
    }

}