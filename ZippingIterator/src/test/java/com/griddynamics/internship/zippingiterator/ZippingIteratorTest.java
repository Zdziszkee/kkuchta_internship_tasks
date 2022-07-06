package com.griddynamics.internship.zippingiterator;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ZippingIteratorTest {
    @Test
    public void testIteration() {

        final Iterator<Integer> iterator1 = List.of(1, 2, 3, 4).iterator();
        final Iterator<String> iterator2 = List.of("one", "two", "three").iterator();

        final ZippingIterator<Integer, String, String> zippingIterator = new ZippingIterator<>(iterator1, iterator2, (integer, s) -> integer + s);

        assertEquals(zippingIterator.next(), "1one");
        assertEquals(zippingIterator.next(), "2two");
        assertEquals(zippingIterator.next(), "3three");

        assertFalse(zippingIterator.hasNext());

        assertThrows(NoSuchElementException.class,zippingIterator::next);
    }


}
