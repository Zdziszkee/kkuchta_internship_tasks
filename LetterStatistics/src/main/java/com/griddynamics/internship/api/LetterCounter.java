package com.griddynamics.internship.api;

import java.util.List;
import java.util.Map;

public interface LetterCounter {
    Map<Character,Integer> count(List<String> lines);
}
