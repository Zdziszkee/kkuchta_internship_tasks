package com.griddynamics.internship;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MessageParser {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("You did not provide path for file so sample file will be used instead.");
        }
        final String file = args.length == 0 ? "test.txt" : args[0];
        final Path textFile = Paths.get(file);

        if (!textFile.getFileName()
                     .toString()
                     .endsWith(".txt")) {
            System.out.println("This file is not a txt file!");
            return;
        }
        try {
            final List<String> lines = Files.readAllLines(textFile);
            if ((lines.size() != 2)) {
                System.out.println("Invalid file format (wrong amount of lines!)");
                return;
            }

            try {
                final int number = Integer.parseInt(lines.get(1));
                final String sentence = lines.get(0);

                final List<String> words = Arrays.stream(sentence.split("\\S")).toList();

                for (String word : words) {
                    if (word.length() > number){
                        throw new WordTooLongException();
                    }
                }
                final List<String> spaces = Arrays.stream(sentence.split("\\s")).toList();



            } catch (NumberFormatException exception) {
                System.out.println("Number line does not contain number!");
            }
        } catch (IOException exception) {
            System.out.println("Error occurred while tried to read file!");
        }

    }
}