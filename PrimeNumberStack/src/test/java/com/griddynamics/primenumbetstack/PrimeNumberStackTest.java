package com.griddynamics.primenumbetstack;

import com.griddynamics.primenumberstack.PrimeNumberStack;
import com.griddynamics.primenumberstack.exceptions.NotPrimeNumberException;
import com.griddynamics.primenumberstack.util.Numbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrimeNumberStackTest {
    @Test
    public void testPush() {

        final PrimeNumberStack integers = new PrimeNumberStack(10);

        assertEquals(integers.size(), 0);
        integers.push(3);
        assertEquals(integers.size(), 1);
        assertThrows(NotPrimeNumberException.class, () -> integers.push(4));
        assertEquals(integers.size(), 1);
    }

    @Test
    public void testPeek() {

        final PrimeNumberStack integers = new PrimeNumberStack(10);

        assertEquals(integers.size(), 0);
        integers.push(3);
        assertEquals(integers.size(), 1);
        integers.pop();
        assertEquals(integers.size(), 0);

    }

    @Test
    public void testIterator() {

        final PrimeNumberStack integers = new PrimeNumberStack(10);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            IntStream.range(0, 100).filter(Numbers::isPrime).forEach(integers::push);
        });


        int count = 0;
        for (Integer integer : integers) {
            count++;
        }
        assertEquals(count, 9);
    }


}
