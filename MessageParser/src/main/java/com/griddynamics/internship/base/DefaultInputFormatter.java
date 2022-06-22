package com.griddynamics.internship.base;

import com.griddynamics.internship.api.InputFormatter;
import com.griddynamics.internship.base.exceptions.WordTooLongException;

import java.util.Arrays;
import java.util.List;

public class DefaultInputFormatter implements InputFormatter {

    @Override
    public List<String> format(String sentence, int number) {

        final List<String> words = Arrays.stream(sentence.split("\\S"))
                                         .toList();

        for (String word : words) {
            if (word.length() > number) {
                throw new WordTooLongException();
            }
        }

        final List<String> spaces = Arrays.stream(sentence.split("\\s"))
                                          .toList();

        return words;

    }
}
