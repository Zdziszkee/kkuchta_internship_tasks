package com.griddynamics.primenumberstack.exceptions;

public class PrimeNumberStackFullException extends RuntimeException{
    public PrimeNumberStackFullException() {
        super("PrimeNumberStack is full and you can't add more elements to it!");
    }
}
