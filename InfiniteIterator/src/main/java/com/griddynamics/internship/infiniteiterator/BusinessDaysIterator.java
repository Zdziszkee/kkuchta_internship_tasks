package com.griddynamics.internship.infiniteiterator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Iterator;

public class BusinessDaysIterator implements Iterator<LocalDate> {

    private LocalDate localDate;

    public BusinessDaysIterator(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public LocalDate next() {
        while (localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            localDate = localDate.plusDays(1);

        }
        return localDate;
    }
}
