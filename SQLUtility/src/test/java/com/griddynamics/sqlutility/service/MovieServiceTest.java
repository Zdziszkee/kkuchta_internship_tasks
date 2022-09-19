package com.griddynamics.sqlutility.service;

import com.griddynamics.sqlutility.base.Database;
import com.griddynamics.sqlutility.model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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

class MovieServiceTest {

    private final static String EXPECTED_NAME = "expectedName";

    private final static int EXPECTED_ID = 1;

    private final static LocalDate EXPECTED_DATE = LocalDate.of(1999, 12, 1);

    private final static int EXPECTED_INCOME = 1000;

    private final static List<Integer> EXPECTED_ACTORS = List.of(1, 2, 3);

    private final static Movie EXPECTED_MOVIE = new Movie(EXPECTED_ID, EXPECTED_DATE, EXPECTED_NAME, EXPECTED_INCOME, EXPECTED_ACTORS);

    private final Database mock = mock(Database.class);

    private MovieService movieService;

    @BeforeEach
    void setUp() {
        movieService = new MovieService(mock);
    }

    @Test
    void findById() {
        given(mock.findOne(eq("SELECT * FROM movies WHERE movieId = ?;"), any(Function.class), eq(EXPECTED_ID))).willReturn(
                Optional.of(EXPECTED_MOVIE));
        given(mock.findMany(eq("SELECT actorId FROM movie_actors JOIN actors ON actors.actorId = movie_actors.actorId WHERE movieId = ?;"),
                any(Function.class), eq(EXPECTED_ID))).willReturn(EXPECTED_ACTORS);
        final Optional<Movie> optionalMovie = movieService.findById(EXPECTED_ID);
        assertTrue(optionalMovie.isPresent());
        assertEquals(optionalMovie.get(), EXPECTED_MOVIE);
    }

    @Test
    void save() {
        final Optional<Movie> optionalMovie = movieService.save(EXPECTED_MOVIE);
        EXPECTED_ACTORS.forEach(actorId -> then(mock).should().execute("INSERT INTO movie_actors VALUES(?,?)", actorId, EXPECTED_ID));
        then(mock).should().execute("INSERT INTO movies VALUES(?,?)", EXPECTED_ID, EXPECTED_NAME);
    }

    @Test
    void findAll() {
        given(mock.findMany(eq("SELECT * FROM actors"), any(Function.class))).willReturn(List.of(EXPECTED_MOVIE));
    }

}