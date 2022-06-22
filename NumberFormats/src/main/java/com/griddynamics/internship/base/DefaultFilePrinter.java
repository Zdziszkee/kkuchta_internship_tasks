package com.griddynamics.internship.base;

import com.griddynamics.internship.api.FilePrinter;
import com.griddynamics.internship.util.Pair;

import java.util.Comparator;
import java.util.List;

public class DefaultFilePrinter implements FilePrinter {
    @Override
    public void print(List<Pair<String, Double>> input) {
        input.stream()
             .sorted((first, second) -> {
                 Double firstValue = first.getValue();
                 Double secondValue = second.getValue();
                 if (firstValue > secondValue) {
                     return 1;
                 } else if (firstValue < secondValue) {
                     return -1;
                 }
                 return 0;
             }).forEach(pair-> System.out.println(pair.getKey()));
    }
}
