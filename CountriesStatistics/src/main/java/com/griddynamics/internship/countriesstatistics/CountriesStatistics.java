package com.griddynamics.internship.countriesstatistics;

import com.griddynamics.internship.countriesstatistics.api.FileLoader;
import com.griddynamics.internship.countriesstatistics.api.FileReader;
import com.griddynamics.internship.countriesstatistics.base.Continent;
import com.griddynamics.internship.countriesstatistics.base.Country;
import com.griddynamics.internship.countriesstatistics.base.DefaultFileLoader;
import com.griddynamics.internship.countriesstatistics.base.DefaultFileReader;
import com.griddynamics.internship.countriesstatistics.base.exceptions.DefaultFileNotFoundException;
import com.griddynamics.internship.countriesstatistics.base.exceptions.WrongFileFormatException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.groupingBy;

public class CountriesStatistics {
    private final FileReader fileReader;
    private final FileLoader fileLoader;

    public CountriesStatistics(FileReader fileReader, FileLoader fileLoader) {
        this.fileReader = fileReader;
        this.fileLoader = fileLoader;
    }

    public static void main(String[] args) {
        final CountriesStatistics countriesStatistics = new CountriesStatistics(new DefaultFileReader(), new DefaultFileLoader());
        countriesStatistics.run(args);
    }

    public void run(String[] args) {
        final String path = args.length < 1 ? "" : args[0];
        try {
            final Path file = fileLoader.load(path);
            final List<Country> countries = fileReader.read(file);
            //#1111111111111111111111111
            final List<Country> sortedByPopulationDescending = countries.stream().sorted((first, second) -> {
                int firstPopulation = first.population();
                int secondPopulation = second.population();
                return Integer.compare(firstPopulation, secondPopulation);
            }).toList();


            //#222222222222222222222

            System.out.println("Max Population: " + sortedByPopulationDescending.stream().max(comparingDouble(Country::population)));
            System.out.println("Min Population: " + sortedByPopulationDescending.stream().min(comparingDouble(Country::population)));


            System.out.println("Max Area: " + sortedByPopulationDescending.stream().max(comparingDouble(Country::area)));
            System.out.println("Min Area: " + sortedByPopulationDescending.stream().max(comparingDouble(Country::area)));

            //#333333333333333333333333333

            final List<Country> sortedByContinentAndArea = countries.stream().limit(20)
                                                                    .sorted(comparing(Country::continent).thenComparingDouble(Country::area)
                                                                                                         .reversed()).toList();

            sortedByContinentAndArea.forEach(country -> System.out.println(sortedByContinentAndArea));

            //#444444444444444444444444444

            System.out.println("Max population: " + sortedByPopulationDescending.stream().max(comparingDouble(Country::population)));

            //#555555555555555555555555555

            final Map<Continent, List<Country>> continentCountries = countries.stream().collect(groupingBy(Country::continent));

            final Map<Continent, Double> continentArea = new HashMap<>();

            continentCountries.forEach((key, value) -> {
                continentArea.put(key, value.stream().mapToDouble(Country::area).sum());
            });

            continentArea.forEach((key, value) -> System.out.println(key + " " + value));


            //#666666666666666666666666666666

            final List<Country> filteredByPopulation = countries.stream().filter(country -> country.population() > 100000000).toList();

            System.out.println(filteredByPopulation);


            //#7777777777777777777777777777777
            final Map<Character, List<Country>> groupByFirstChar = countries.stream()
                                                                            .collect(groupingBy(country -> country.name().charAt(0)));
            System.out.println(groupByFirstChar);


        }
        catch (DefaultFileNotFoundException e) {
            System.out.println("Default file could not been found");
        }
        catch (WrongFileFormatException e) {
            System.out.println("Given file has wrong format");
        }
        catch (IOException e) {
            System.out.println("Could not read contents of given file");
        }
    }
}
