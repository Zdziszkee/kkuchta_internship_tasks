package com.griddynamics.flatteningiterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FlatteningIterator<T> implements Iterator<T> {
    private final Iterator<T>[] iterators;
    private int index = 0;

    @SafeVarargs
    public FlatteningIterator(Iterator<T>... iterators) {
        this.iterators = iterators;
    }

    @Override
    public boolean hasNext() {

        final Iterator<Iterator<T>> iteratorsIterator = Arrays.stream(iterators)
                                                              .iterator();
        while (iteratorsIterator.hasNext()) {
            final Iterator<T> iterator = iteratorsIterator.next();
            if (iterator.hasNext()) {
                return true;
            }

        }
        return false;
    }

    @Override
    public T next() {
        final Iterator<T> iterator = iterators[index];
        if (iterator.hasNext()) {
            T next = iterator.next();
            if (!iterator.hasNext() && (index + 1) < iterators.length) {
                index++;
            }
            return next;
        } else {
            throw new NoSuchElementException();
        }
    }

}
