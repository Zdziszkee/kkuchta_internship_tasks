package com.griddynamics.internship;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class PrimeNumberStackTest {
    @Test
    public void testInput() {
        final PrimeNumberStack integers = new PrimeNumberStack(10);
        Stream.generate(() -> ThreadLocalRandom.current()
                                               .nextInt(10))
              .limit(10)
              .forEach(integers::push);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }
}
