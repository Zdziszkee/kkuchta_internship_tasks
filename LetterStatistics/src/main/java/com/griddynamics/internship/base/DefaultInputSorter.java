package com.griddynamics.internship.base;

import com.griddynamics.internship.api.InputSorter;

import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultInputSorter implements InputSorter {
    @Override
    public Map<Character, Integer> sort(Map<Character, Integer> input) {
        final Map<Character, Integer> sortedInput = new LinkedHashMap<>();
        input.entrySet()
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
             })
             .forEach(entry -> sortedInput.put(entry.getKey(), entry.getValue()));
        return sortedInput;
    }
}
