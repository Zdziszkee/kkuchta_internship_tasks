package com.griddynamics.internship.base;

import com.griddynamics.internship.api.FilePrinter;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class DefaultFilePrinter implements FilePrinter {
    @Override
    public void print(TreeMap<String, Double> input) {
        final Iterator<Map.Entry<String, Double>> iterator = input.entrySet()
                                                                  .iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Double> next = iterator.next();
            System.out.print(next.getKey() + (iterator.hasNext() ? ", " : ""));
        }
    }
}
