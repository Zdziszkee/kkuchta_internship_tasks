package com.griddynamics.internship.countriesstatistics.base;

import com.griddynamics.internship.countriesstatistics.api.FileReader;
import com.griddynamics.internship.countriesstatistics.base.exceptions.InvalidLineFormatException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DefaultFileReader implements FileReader {

    private static final Pattern PATTERN = Pattern.compile(";");

    @Override
    public List<Country> read(Path path) throws IOException {

        final List<String> lines = Files.readAllLines(path);


        return lines.stream().map(line -> {

            final String[] words = PATTERN.split(line);

            if (words.length != 5) {
                throw new InvalidLineFormatException();
            }
            final String name = words[1];
            final Continent continent = Continent.getContinentByAlias(words[2]);
            final double area = Double.parseDouble(words[3]);
            final int population = Integer.parseInt(words[4]);

            return new Country(name, continent, population, area);
        }).collect(Collectors.toList());
    }
}
