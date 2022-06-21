package com.griddynamics.internship.base;

import com.griddynamics.internship.api.CharacterPrinter;

import java.util.Map;

public class DefaultCharacterPrinter implements CharacterPrinter {


    @Override
    public void print(Map<Character, Integer> characterCountMap, int limit) {
        characterCountMap.entrySet()
                         .stream()
                         .sorted((first, second) -> {
                             Integer firstValue = first.getValue();
                             Integer secondValue = second.getValue();
                             if (firstValue < secondValue) {
                                 return 1;
                             } else if (firstValue.equals(secondValue)) {
                                 return 0;
                             }
                             return -1;
                         }).limit(10).forEach((entry) -> System.out.println(entry.getValue() + ": " + entry.getKey()));
    }
}
