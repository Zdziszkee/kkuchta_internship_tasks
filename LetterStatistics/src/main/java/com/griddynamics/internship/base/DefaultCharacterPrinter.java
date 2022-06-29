package com.griddynamics.internship.base;

import com.griddynamics.internship.api.CharacterPrinter;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class DefaultCharacterPrinter implements CharacterPrinter {


    @Override
    public void print(TreeMap<Character, Integer> characterCountMap, int limit) {
        characterCountMap.descendingMap().entrySet()
                         .stream()
                         .limit(limit)
                         .forEach((entry) -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
