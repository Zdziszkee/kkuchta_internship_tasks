package com.griddynamics.flatteningiterator;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class FlatteningIteratorTest {
    @Test
    public void testIteration() {

        Iterator<Integer> iterator = List.of(1, 2).iterator();
        Iterator<Integer> iterator1 = List.of(2, 3, 4).iterator();
        Iterator<Integer> iterator2 = Collections.emptyIterator();
        Iterator<Integer> iterator3 = List.of(9, 10, 11, 12, 13).iterator();
        Iterator<Integer> iterator4 = List.of(14, 15, 16, 17, 18, 19).iterator();

        Iterator<Integer> flatteningIterator = new FlatteningIterator<>(iterator, iterator1, iterator2, iterator3, iterator4);
        assertEquals(flatteningIterator.next(),1);
        assertEquals(flatteningIterator.next(),2);
        assertTrue(flatteningIterator.hasNext());

    }

    @Test
    public void testNext(){
        FlatteningIterator<Object> flatteningIterator = new FlatteningIterator<>();
        assertThrows(NoSuchElementException.class, flatteningIterator::next);
    }

    @Test
    public void testCreation(){
        Iterator<Integer> iterator = List.of(1, 2).iterator();
        Iterator<Integer> flatteningIterator = new FlatteningIterator<>(iterator);
        assertDoesNotThrow(()->{
            flatteningIterator.next();
            flatteningIterator.next();
        });

    }
}
