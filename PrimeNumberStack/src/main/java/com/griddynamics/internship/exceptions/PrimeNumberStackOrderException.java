package com.griddynamics.internship.exceptions;

public class PrimeNumberStackOrderException extends RuntimeException {
    public PrimeNumberStackOrderException() {
        super("Given number is not bigger than previous one!");
    }
}
