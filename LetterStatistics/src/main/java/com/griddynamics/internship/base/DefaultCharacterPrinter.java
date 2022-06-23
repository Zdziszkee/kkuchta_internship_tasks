package com.griddynamics.internship.base;

import com.griddynamics.internship.api.CharacterPrinter;

import java.util.Map;

public class DefaultCharacterPrinter implements CharacterPrinter {


    @Override
    public void print(Map<Character, Integer> characterCountMap, int limit) {
        characterCountMap.entrySet()
                         .stream()
                         .sorted(Map.Entry.comparingByValue())
                         .limit(limit)
                         .forEach((entry) -> System.out.println(entry.getValue() + ": " + entry.getKey()));
    }
}
