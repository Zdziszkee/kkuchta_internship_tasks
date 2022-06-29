package com.griddynamics.internship;

import com.griddynamics.internship.exceptions.NotPrimeNumberException;

import java.util.Iterator;

import static com.griddynamics.internship.util.Numbers.isPrime;

public class PrimeNumberStack implements Iterable<Integer> {
    private final Integer[] primes;

    private int size;

    public PrimeNumberStack(final int size) {
        primes = new Integer[size];
    }


    public int push(int n) {
        if (!isPrime(n)) {
            throw new NotPrimeNumberException();
        }
        if (size  > primes.length) {
            throw new IndexOutOfBoundsException("Prime number stack is full");
        }
        primes[size ] = n;
        size++;
        return n;
    }

    public int pop() {
        if (size <= 0) {
            throw new IndexOutOfBoundsException("Prime number stack is empty");
        }
        int index = size - 1;
        Integer prime = this.primes[index];
        this.primes[index] = null;
        size--;
        return prime;

    }

    public int peek() {
        if (size <= 0) {
            throw new IndexOutOfBoundsException("Prime number stack is empty");
        }
        return this.primes[size - 1];
    }

    public int size() {
        return size;
    }


    @Override
    public Iterator<Integer> iterator() {
        return new StackIterator();
    }


    private class StackIterator implements Iterator<Integer> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return (index + 1) < size;
        }

        @Override
        public Integer next() {
            final int previous = index;
            index++;
            return primes[previous];
        }

    }

}
