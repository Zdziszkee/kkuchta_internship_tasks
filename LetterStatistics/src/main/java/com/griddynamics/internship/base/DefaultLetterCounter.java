package com.griddynamics.internship.base;

import com.griddynamics.internship.api.FileReader;
import com.griddynamics.internship.api.LetterCounter;

import java.util.*;

public class DefaultLetterCounter implements LetterCounter {
    @Override
    public TreeMap<Character, Integer> count(List<String> lines) {

        final TreeMap<Character, Integer> characterCountMap = new TreeMap<>();
        for (String line : lines) {
            String formattedLine = line.toLowerCase();
            for (char character : formattedLine.toCharArray()) {
                if (!Character.isAlphabetic(character)) {
                    continue;
                }
                characterCountMap.merge(character, 1, Integer::sum);
            }
        }
        return characterCountMap;
    }
}
