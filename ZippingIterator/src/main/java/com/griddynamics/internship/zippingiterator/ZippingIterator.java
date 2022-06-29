package com.griddynamics.internship.zippingiterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;

public class ZippingIterator<A, B, C> implements Iterator<C> {

    private final Iterator<A> first;
    private final Iterator<B> second;

    private final BiFunction<A, B, C> function;

    public ZippingIterator(Iterator<A> first, Iterator<B> second, BiFunction<A, B, C> function) {
        this.first = first;
        this.second = second;
        this.function = function;
    }

    @Override
    public boolean hasNext() {
        return (first.hasNext()) && second.hasNext();
    }

    @Override
    public C next() {

        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        final A firstNext = first.next();
        final B secondNext = second.next();

        return function.apply(firstNext, secondNext);


    }
}
