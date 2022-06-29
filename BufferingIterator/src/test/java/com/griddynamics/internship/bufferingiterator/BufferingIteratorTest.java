package com.griddynamics.internship.bufferingiterator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BufferingIteratorTest {
    @Test
    public void testIteration() {
        final BufferingIterator<Integer> bufferingIterator = new BufferingIterator<>(List.of(1, 2, 3, 4, 5, 6, 6, 7, 8, 9)
                                                                                         .iterator(), 3);

        while (bufferingIterator.hasNext()) {
            System.out.println(bufferingIterator.next());
        }
    }

    @Test
    public void testOutput() {
        final BufferingIterator<Integer> bufferingIterator = new BufferingIterator<>(List.of(1, 2, 3, 4, 5, 6, 6, 7, 8, 9)
                                                                                         .iterator(), 3);
        List<List<Integer>> list = new ArrayList<>();
        while (bufferingIterator.hasNext()) {
            list.add(bufferingIterator.next());
        }
        assert list.size() == 4;

    }
}
