package com.griddynamics.internship.base;

import com.griddynamics.internship.api.FileReader;
import com.griddynamics.internship.base.exceptions.InvalidFileFormatException;
import com.griddynamics.internship.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DefaultFileReader implements FileReader {
    @Override
    public List<Pair<String, Double>> read(Path file) throws IOException {
        final List<String> lines = Files.readAllLines(file);
        final List<Pair<String, Double>> input = new ArrayList<>();

        for (String line : lines) {
            try {
                double value = Double.parseDouble(line);
                input.add(new Pair<>(line,value));
            }
            catch (NumberFormatException exception) {
                throw new InvalidFileFormatException("One of the lines in given file is not parsable to double!");
            }

        }

        return input;
    }
}
