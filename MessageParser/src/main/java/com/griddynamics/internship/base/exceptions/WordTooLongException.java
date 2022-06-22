package com.griddynamics.internship.base.exceptions;

public class WordTooLongException extends RuntimeException {
    public WordTooLongException() {
        super("Given number is smaller than one of the words length!");
    }
}
