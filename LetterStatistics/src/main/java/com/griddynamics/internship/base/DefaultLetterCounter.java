package com.griddynamics.internship.base;

import com.griddynamics.internship.api.FileReader;
import com.griddynamics.internship.api.LetterCounter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DefaultLetterCounter implements LetterCounter {
    @Override
    public Map<Character, Integer> count(List<String> lines) {
        final Map<Character, Integer> characterCountMap = new LinkedHashMap<>();
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
