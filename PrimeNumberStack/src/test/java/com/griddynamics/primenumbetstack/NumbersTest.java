package com.griddynamics.primenumbetstack;

import com.griddynamics.primenumberstack.util.Numbers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumbersTest {

    @Test
    public void testIsPrime() {

        final int notPrime = 4;
        final int prime = 7;

        assertTrue(Numbers.isPrime(7));
        assertFalse(Numbers.isPrime(4));
    }
}
