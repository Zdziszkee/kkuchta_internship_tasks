package com.griddynamics.internship.api;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public interface LetterCounter {
    TreeMap<Character,Integer> count(List<String> lines);
}
