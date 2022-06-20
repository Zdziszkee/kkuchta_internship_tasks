package com.griddynamics.internship;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LetterStatistics {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("You did not provide path for file so sample file will be used instead.");
        }

        ClassLoader loader = Thread.currentThread()
                                   .getContextClassLoader();
        URL defaultFileURL = loader.getResource("test.txt");

        if (defaultFileURL == null) {
            System.out.println("Default txt file could not be find!");
            return;
        }
        final String file = args.length == 0 ? defaultFileURL.getPath() : args[0];


        final Path textFile = Paths.get(file);

        if (!textFile.getFileName()
                     .toString()
                     .endsWith(".txt")) {
            System.out.println("This file is not a txt file!");
            return;
        }

        try {
            final List<String> lines = Files.readAllLines(textFile);
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
        catch (IOException e) {
            System.out.println("Error occurred while trying to read the file!");
        }
    }
}