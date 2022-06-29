package com.griddynamics.primenumbetstack;

import com.griddynamics.primenumberstack.PrimeNumberStack;
import com.griddynamics.primenumberstack.exceptions.NotPrimeNumberException;
import com.griddynamics.primenumberstack.util.Numbers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.stream.IntStream;

public class PrimeNumberStackTest {
    @Test
    public void testPush() {

        final PrimeNumberStack integers = new PrimeNumberStack(10);

        assert integers.size() == 0;
        integers.push(3);
        assert integers.size() == 1;
        Assertions.assertThrows(NotPrimeNumberException.class, () -> integers.push(4));
        assert integers.size() == 1;
    }

    @Test
    public void testPeek() {

        final PrimeNumberStack integers = new PrimeNumberStack(10);

        assert integers.size() == 0;
        integers.push(3);
        assert integers.size() == 1;
        integers.pop();
        assert integers.size() == 0;

    }

    @Test

    public void testIterator() {

        final PrimeNumberStack integers = new PrimeNumberStack(10);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            IntStream.range(0, 100)
                     .filter(Numbers::isPrime)
                     .forEach(integers::push);
        });


        int count = 0;
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        assert count == 9;
    }
}
