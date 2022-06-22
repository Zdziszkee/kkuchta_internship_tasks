package com.griddynamics.internship.base;

import com.griddynamics.internship.api.InputFormatter;
import com.griddynamics.internship.base.exceptions.WordTooLongException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class DefaultInputFormatter implements InputFormatter {
    private static final Pattern WORD_REGEX = Pattern.compile("\\s");
    private static final Pattern SPACE_REGEX = Pattern.compile("\\S+");

    @Override
    public List<String> format(String sentence, int maxLineSize) {

        final List<String> words = new ArrayList<>(Arrays.stream(WORD_REGEX.split(sentence, 0))
                                                         .toList());

        for (String word : words) {
            if (word.length() > maxLineSize) {
                throw new WordTooLongException();
            }
        }

        final List<String> spaces = new ArrayList<>(Arrays.stream(SPACE_REGEX.split(sentence, 0))
                                                          .toList());

        List<String> formattedLines = new ArrayList<>();

        while (spaces.size() != 0) {
            formattedLines.add(buildSentence(words, spaces, maxLineSize));
        }


        return formattedLines;

    }

    private String buildSentence(List<String> words, List<String> spaces, int maxLineSize) {
        final StringBuilder stringBuilder = new StringBuilder();
        final List<String> wordsToRemove = new ArrayList<>();
        final List<String> spacesToRemove = new ArrayList<>();

        for (int i = 0; i < spaces.size(); i++) {
            if (stringBuilder.toString()
                             .length() >= maxLineSize) {
                break;
            }
            String space = spaces.get(i);
            spacesToRemove.add(space);

            String word = "";
            if (words.size() > i) {
                word = words.get(i);
                wordsToRemove.add(word);
            }
            stringBuilder.append(space)
                         .append(word);
        }
        final Iterator<String> wordsIterator = words.iterator();
        while (wordsIterator.hasNext()) {
            final String nextWord = wordsIterator.next();
            if (wordsToRemove.contains(nextWord)) {
                wordsIterator.remove();
                wordsToRemove.remove(nextWord);
            }
        }
        final Iterator<String> spacesIterator = spaces.iterator();
        while (spacesIterator.hasNext()) {
            final String nextWord = spacesIterator.next();
            if (spacesToRemove.contains(nextWord)) {
                spacesIterator.remove();
                spacesToRemove.remove(nextWord);
            }
        }
        return stringBuilder.toString();
    }
}
