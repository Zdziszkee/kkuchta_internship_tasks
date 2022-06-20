package com.griddynamics.internship;

public class WordTooLongException extends RuntimeException {
    public WordTooLongException() {
        super("Given number is smaller than one of the words length!");
    }
}
