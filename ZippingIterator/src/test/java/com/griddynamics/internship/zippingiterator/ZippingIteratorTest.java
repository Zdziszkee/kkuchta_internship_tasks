package com.griddynamics.internship.zippingiterator;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

public class ZippingIteratorTest {
    @Test
    public void testIteration() {
        final Iterator<Integer> iterator1 = List.of(1, 2, 3, 4)
                                                .iterator();
        Iterator<String> iterator2 = List.of("one", "two", "three")
                                         .iterator();

        final ZippingIterator<Integer, String, String> zippingIterator = new ZippingIterator<>(iterator1, iterator2, (integer, s) -> integer + s);

        while (zippingIterator.hasNext()) {
            System.out.println(zippingIterator.next());
        }
    }
}
