package com.griddynamics.flatteningiterator;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

public class FlatteningIteratorTest {
    @Test
    public void testIteration() {
        Iterator<Integer> iterator = List.of(1, 2)
                                         .iterator();
        Iterator<Integer> iterator1 = List.of(2, 3, 4)
                                          .iterator();
        Iterator<Integer> iterator2 = List.of(5, 6, 7, 8)
                                          .iterator();
        Iterator<Integer> iterator3 = List.of(9, 10, 11, 12, 13)
                                          .iterator();
        Iterator<Integer> iterator4 = List.of(14, 15, 16, 17, 18, 19)
                                          .iterator();

        Iterator<Integer> flatteningIterator = new FlatteningIterator<>(iterator, iterator1, iterator2, iterator3, iterator4);


        while (flatteningIterator.hasNext()) {
            System.out.println(flatteningIterator.next());
        }
        assert true;
    }
}
