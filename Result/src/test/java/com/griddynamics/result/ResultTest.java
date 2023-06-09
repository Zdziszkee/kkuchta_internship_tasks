package com.griddynamics.result;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResultTest {

    @Test
    public void testOk() throws Throwable {

        assertDoesNotThrow(() -> Result.of(() -> 1).unwrap());
    }

    @Test
    public void testError() {

        assertThrows(IllegalArgumentException.class, () -> Result.of(() -> {
            throw new IllegalArgumentException();
        }).unwrap());
    }

    @Test
    public void testMap() throws Throwable {

        Integer result = Result.of(() -> 1).map(number -> number * 2).unwrap();
        assertEquals(result, 2);
    }

    @Test
    public void flatMap() throws Throwable {

        assertEquals(Result.of(() -> "string").flatMap((string) -> Result.of(string::length)).unwrap(), 6);
    }

    @Test
    public void mapError() {

        assertThrows(IllegalArgumentException.class, () -> Result.of(() -> {
            throw new IllegalArgumentException();
        }).mapError(throwable -> new IllegalArgumentException()).unwrap());
    }

    @Test
    public void unwrap() {

        assertDoesNotThrow(() -> {
            String unwrap = Result.of(() -> "string").unwrap();
            assertEquals("string", unwrap);
            return unwrap;
        });

        assertThrows(NullPointerException.class, () -> {
            Result.of(() -> {
                throw new NullPointerException();
            }).unwrap();
        });
    }

    @Test
    public void error() {

        assertThrows(NullPointerException.class, () -> Result.error(new NullPointerException()).unwrap());
    }

}
