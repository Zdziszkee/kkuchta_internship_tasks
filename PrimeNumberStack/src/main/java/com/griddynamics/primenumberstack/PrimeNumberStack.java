package com.griddynamics.primenumberstack;

import com.griddynamics.primenumberstack.exceptions.NotPrimeNumberException;
import com.griddynamics.primenumberstack.exceptions.PrimeNumberStackFullException;
import com.griddynamics.primenumberstack.exceptions.PrimeNumberStackOrderException;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.griddynamics.primenumberstack.util.Numbers.isPrime;

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
        if (size > primes.length) {
            throw new PrimeNumberStackFullException();
        }
        if (size != 0 && primes[size - 1] >= n) {
            throw new PrimeNumberStackOrderException();
        }
        primes[size] = n;
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
            if (index >= primes.length) {
                throw new NoSuchElementException();
            }
            final int previous = index;
            index++;
            return primes[previous];
        }

    }

}
