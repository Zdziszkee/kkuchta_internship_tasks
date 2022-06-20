package com.griddynamics.internship;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterStatistics {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("You need to provide target txt file path as program argument!");
            return;
        }

        final Path textFile = Paths.get(args[0]);

        if (!textFile.getFileName().toString().endsWith(".txt")) {
            System.out.println("This file is not a txt file!");
            return;
        }

        try {
            final List<String> lines = Files.readAllLines(textFile);
            final Map<Character, Integer> characterCountMap = new HashMap<>();
            for (String line : lines) {
                for (char character : line.toCharArray()) {
                    if (!Character.isAlphabetic(character)) {
                        continue;
                    }
                    characterCountMap.merge(character, 1, Integer::sum);
                }
            }

            characterCountMap.forEach((character, count) -> System.out.println(character + ": " + count));

        }
        catch (IOException e) {
            System.out.println("Error occurred while trying to read the file!");
        }
    }
}