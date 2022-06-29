package com.griddynamics.internship.exceptions;

public class NotPrimeNumberException extends RuntimeException {
    public NotPrimeNumberException() {
        super("Provided number is not a prime number!");
    }
}
