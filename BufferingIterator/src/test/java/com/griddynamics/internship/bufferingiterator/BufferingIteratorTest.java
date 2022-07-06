package com.griddynamics.internship.bufferingiterator;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class BufferingIteratorTest {
    @Test
    public void testIteration(){

        final BufferingIterator<Integer> bufferingIterator = new BufferingIterator<>(List.of(1, 2, 3, 4, 5).iterator(), 3);
        assertTrue(bufferingIterator.hasNext());
        List<Integer> next = bufferingIterator.next();
        assertEquals(next.size(),3);
        assertTrue(bufferingIterator.hasNext());
        List<Integer> next2 = bufferingIterator.next();
        assertTrue(next2.size()<=3);


    }

    @Test
    public void testEmpty(){

        BufferingIterator<Object> bufferingIterator = new BufferingIterator<>(Collections.emptyIterator(),1);
        assertFalse(bufferingIterator.hasNext());
        assertThrows(NoSuchElementException.class, bufferingIterator::next);
    }
}
