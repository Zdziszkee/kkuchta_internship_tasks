package com.griddynamics.internship.infiniteiterator;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class BusinessDaysIteratorTest {
    @Test
    public void testCorrectness() {

        final BusinessDaysIterator businessDaysIterator = new BusinessDaysIterator(LocalDate.now());

        for (int i = 0; i < 100; i++) {
            final LocalDate next = businessDaysIterator.next();

            final DayOfWeek dayOfWeek = next.getDayOfWeek();

            assert !(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY);
        }
    }
}
