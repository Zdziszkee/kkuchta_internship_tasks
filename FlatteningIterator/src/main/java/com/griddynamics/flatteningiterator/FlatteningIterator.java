package com.griddynamics.flatteningiterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FlatteningIterator<T> implements Iterator<T> {
    private final Iterator<T>[] iterators;

    private Iterator<T> current;

    private int iteratorIndex;

    @SafeVarargs
    public FlatteningIterator(Iterator<T>... iterators) {
        this.iterators = iterators;
        if (iterators.length > 0) {
            current = iterators[0];
            iteratorIndex = 0;
        }
    }

    @Override
    public boolean hasNext() {

        boolean hasNext = current.hasNext();
        final int nextIteratorIndex = iteratorIndex + 1;
        if (hasNext) {
            return true;
        } else if (nextIteratorIndex < iterators.length) {
            this.current = iterators[nextIteratorIndex];
            iteratorIndex = nextIteratorIndex;
            return hasNext();
        }

        return false;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("there is no more elements");
        }
        return current.next();
    }

}
