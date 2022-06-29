package com.griddynamics.internship.base;

import com.griddynamics.internship.api.FileReader;
import com.griddynamics.internship.base.exceptions.InvalidFileFormatException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.TreeMap;

public class DefaultFileReader implements FileReader {
    @Override
    public TreeMap<String, Double> read(Path file) throws IOException {
        final List<String> lines = Files.readAllLines(file);
        final TreeMap<String, Double> input = new TreeMap<>();

        for (String line : lines) {
            try {
                double value = Double.parseDouble(line);
                input.put(line, value);
            }
            catch (NumberFormatException exception) {
                throw new InvalidFileFormatException("One of the lines in given file is not parsable to double!");
            }

        }

        return input;
    }
}
