package com.griddynamics.internship.infiniteiterator;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BusinessDaysIteratorTest {
    @Test
    public void testIteration() {
        BusinessDaysIterator businessDaysIterator = new BusinessDaysIterator(LocalDate.now());

        for (int i = 0; i < 100; i++) {
            assertTrue(businessDaysIterator.hasNext());
            LocalDate next = businessDaysIterator.next();
            DayOfWeek dayOfWeek = next.getDayOfWeek();
            assertFalse(dayOfWeek==DayOfWeek.SATURDAY||dayOfWeek==DayOfWeek.SUNDAY);
        }
    }
}
