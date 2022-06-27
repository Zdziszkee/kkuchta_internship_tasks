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

        final List<String> words = Arrays.asList(WORD_REGEX.split(sentence, 0));

        for (String word : words) {
            if (word.length() > maxLineSize) {
                throw new WordTooLongException();
            }
        }

        final List<String> spaces = Arrays.asList(SPACE_REGEX.split(sentence, 0));

        final List<String> stringSequence = new ArrayList<>();

        for (int i = 0; i < spaces.size(); i++) {
            String space = spaces.get(i);
            String word = words.get(i);
            stringSequence.add(space);
            stringSequence.add(word);
        }

        List<String> formattedLines = new ArrayList<>();

        while (stringSequence.size() > 0) {
            formattedLines.add(buildSentence(stringSequence, maxLineSize));
        }


        return formattedLines;

    }

    private String buildSentence(List<String> stringSequence, int maxLineSize) {
        final StringBuilder stringBuilder = new StringBuilder();

        final Iterator<String> stringIterator = stringSequence.iterator();

        while (stringIterator.hasNext()) {
            final String nextString = stringIterator.next();

            int index = stringSequence.indexOf(nextString);
            int remainingSpace = maxLineSize - stringBuilder.length();
            int length = nextString.length();

            if (nextString.length() <= remainingSpace) {
                stringBuilder.append(nextString);
                stringIterator.remove();
            } else if (nextString.isBlank()) {
                int toCut = length - remainingSpace;
                String substring = nextString.substring(length - toCut + 1);
                stringSequence.set(index, substring);
                stringBuilder.append(nextString.substring(0, length - toCut + 1));
            }else break;

        }


        return stringBuilder.toString();
    }
}
